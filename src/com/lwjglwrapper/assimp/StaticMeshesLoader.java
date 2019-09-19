/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.assimp;

import com.lwjglwrapper.exceptions.AssimpException;
import com.lwjglwrapper.opengl.objects.Texture2D;
import com.lwjglwrapper.opengl.objects.TextureData;
import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.utils.IColor;
import com.lwjglwrapper.utils.Utils;
import com.lwjglwrapper.utils.collections.FloatArrayStack;
import com.lwjglwrapper.utils.collections.IntArrayStack;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.AIColor4D;
import org.lwjgl.assimp.AIFace;
import org.lwjgl.assimp.AIMaterial;
import org.lwjgl.assimp.AIMesh;
import org.lwjgl.assimp.AIScene;
import org.lwjgl.assimp.AIString;
import org.lwjgl.assimp.AIVector3D;
import org.lwjgl.assimp.Assimp;

/**
 *
 * @author Welcome
 */
public class StaticMeshesLoader {

    public static Mesh[] load(Class cl, String resourcePath, String texturesDir) throws AssimpException, IOException {
        return load(cl, resourcePath, texturesDir, Assimp.aiProcess_JoinIdenticalVertices | Assimp.aiProcess_Triangulate | Assimp.aiProcess_FixInfacingNormals);
    }

    public static Mesh[] load(Class cl, String resourcePath, String texturesDir,
            int flags) throws AssimpException, IOException {
        ByteBuffer buffer = Utils.ioResourceToByteBuffer(cl.getResourceAsStream(resourcePath), 8 * 1024);
        Optional<String> optionalExtension = extension(resourcePath);
        if (!optionalExtension.isPresent()) {
            throw new AssimpException("No extension found in resource path");
        }
        String ext = optionalExtension.get();
        AIScene scene = Assimp.aiImportFileFromMemory(buffer, flags, ext);
        if (scene == null) {
            throw new AssimpException("Error loading model");
        }
        int materialsCount = scene.mNumMaterials();
        PointerBuffer materials = scene.mMaterials();
        List<Material> materialList = new ArrayList<>();
        for (int i = 0; i < materialsCount; i++) {
            AIMaterial mat = AIMaterial.create(materials.get(i));
            process(cl, mat, materialList, texturesDir);
        }

        int meshesCount = scene.mNumMeshes();
        PointerBuffer meshes = scene.mMeshes();
        Mesh[] meshesArray = new Mesh[meshesCount];
        for (int i = 0; i < meshesCount; i++) {
            AIMesh mesh = AIMesh.create(materials.get(i));
            meshesArray[i] = process(mesh, materialList);
        }
        return meshesArray;
    }

    private static Optional<String> extension(String path) {
        return Optional.ofNullable(path)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(path.lastIndexOf(".") + 1));
    }

    private static void process(Class cl, AIMaterial material,
            List<Material> materialList,
            String texturesDir) {
        IColor ambient = Material.DEFAULT_COLOR;
        IColor diffuse = Material.DEFAULT_COLOR;
        IColor specular = Material.DEFAULT_COLOR;

        AIString path = AIString.calloc();
        Assimp.aiGetMaterialTexture(material, Assimp.aiTextureType_DIFFUSE, 0, path, (IntBuffer) null, null, null, null, null, null);
        Texture2D texture = new Texture2D(TextureData.fromResource(cl, texturesDir + "/" + path.dataString()));

        AIColor4D tempColor = AIColor4D.create();

        if (Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_AMBIENT, Assimp.aiTextureType_NONE, 0, tempColor) == Assimp.aiReturn_SUCCESS) {
            ambient = IColor.assimp(tempColor);
        }
        if (Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_DIFFUSE, Assimp.aiTextureType_NONE, 0, tempColor) == Assimp.aiReturn_SUCCESS) {
            diffuse = IColor.assimp(tempColor);
        }
        if (Assimp.aiGetMaterialColor(material, Assimp.AI_MATKEY_COLOR_SPECULAR, Assimp.aiTextureType_NONE, 0, tempColor) == Assimp.aiReturn_SUCCESS) {
            specular = IColor.assimp(tempColor);
        }

        materialList.add(new Material(texture, ambient, diffuse, specular));

    }

    private static Mesh process(AIMesh mesh, List<Material> materialList) {
        FloatArrayStack vertices = processVertices(mesh);
        FloatArrayStack normals = processNormals(mesh);
        FloatArrayStack texCoords = processTexCoords(mesh);
        IntArrayStack indices = processIndices(mesh);
        
        int matIdx = mesh.mMaterialIndex();
        
        
        return new Mesh(vertices, normals, texCoords, indices, materialList.get(matIdx));
    }

    private static FloatArrayStack processVertices(AIMesh mesh) {
        AIVector3D.Buffer vertices = mesh.mVertices();
        FloatArrayStack array = new FloatArrayStack(vertices.remaining());
        while (vertices.remaining() > 0) {
            array.put(vertices.x(), vertices.y(), vertices.z());
        }
        return array;
    }

    private static FloatArrayStack processNormals(AIMesh mesh) {
        AIVector3D.Buffer vertices = mesh.mNormals();
        FloatArrayStack array = new FloatArrayStack(vertices.remaining());
        while (vertices.remaining() > 0) {
            array.put(vertices.x(), vertices.y(), vertices.z());
        }
        return array;
    }

    private static FloatArrayStack processTexCoords(AIMesh mesh) {
        AIVector3D.Buffer texCoords = mesh.mTextureCoords(0);
        if(texCoords == null)   return null;
        FloatArrayStack array = new FloatArrayStack(texCoords.remaining());
        while (texCoords.remaining() > 0) {
            array.put(texCoords.x(), texCoords.y());
        }
        return array;
    }

    private static IntArrayStack processIndices(AIMesh mesh) {
        AIFace.Buffer faces = mesh.mFaces();
        IntArrayStack array = new IntArrayStack(mesh.mNumFaces() * 3);
        for (int i = 0; i < mesh.mNumFaces(); i++) {
            AIFace face = faces.get(i);
            IntBuffer buffer = face.mIndices();
            array.put(buffer.get(0), buffer.get(1), buffer.get(2));
        }
        return array;
    }

    public static class Mesh {

        private FloatArrayStack vertices;
        private FloatArrayStack normals;
        private FloatArrayStack texCoords;
        private IntArrayStack indices;
        private Material material;

        public Mesh(FloatArrayStack vertices, FloatArrayStack normals,
                FloatArrayStack texCoords, IntArrayStack indices, Material material) {
            this.vertices = vertices;
            this.normals = normals;
            this.texCoords = texCoords;
            this.indices = indices;
            this.material = material;
        }

        public VAO toVAO(VAO src) {
            src.bind();
            src.createAttribute(0, vertices.getArray(), 3);
            if(normals != null)     src.createAttribute(1, normals.getArray(), 3);
            if(texCoords != null)   src.createAttribute(2, texCoords.getArray(), 3);
            src.createIndexBuffer(indices.getArray());
            src.unbind();
            
            return src;
        }
        
        
    }

    public static class Material {

        private static IColor DEFAULT_COLOR = IColor.BLACK;

        private Texture2D texture;
        private IColor ambient, diffuse, specular;

        public Material(Texture2D texture, IColor ambient, IColor diffuse,
                IColor specular) {
            this.texture = texture;
            this.ambient = ambient;
            this.diffuse = diffuse;
            this.specular = specular;
        }

    }
}
