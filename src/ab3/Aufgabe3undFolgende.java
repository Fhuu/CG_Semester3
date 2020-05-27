package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

public class
Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private Texture brickTex;
	private Texture brickTexLow;
	private int vao1Id;
	private int vao2Id;

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		brickTex = new Texture("greyBrick.jpg");
		brickTexLow = new Texture("minecraftTexture.jpg");

/**
 * VAO can contains more than one VBO,
 * VBO can be the shape of object, and also the color
 * 	or in short holds information about vertices.
 * which means VAO can be made once and with more than one VBO
 */

		float[] piramiden = {
				//back
				0,0,0,
				0.3f, -0.3f, -0.3f,
				-0.3f, -0.3f, -0.3f,

				//front
				0,0,0,
				-0.3f, -0.3f, 0.3f,
				0.3f, -0.3f, 0.3f,

				//left
				0,0,0,
				-0.3f, -0.3f,-0.3f,
				-0.3f, -0.3f, 0.3f,

				//right
				0,0,0,
				0.3f, -0.3f, 0.3f,
				0.3f, -0.3f,-0.3f,

				//bottom left
				-0.3f,-0.3f,0.3f,
				-0.3f,-0.3f,-0.3f,
				0.3f,-0.3f,0.3f,

				//bottom right
				-0.3f,-0.3f,-0.3f,
				0.3f,-0.3f,-0.3f,
				0.3f,-0.3f,0.3f
		};

		float[] wurfel = {
				//back 1
				0.3f, -0.3f, -0.3f,
				-0.3f, -0.3f, -0.3f,
				-0.3f, 0.3f, -0.3f,

				//back 2
				-0.3f, 0.3f, -0.3f,
				0.3f,0.3f,-0.3f,
                0.3f, -0.3f, -0.3f,

				//right 1
                0.3f, -0.3f, -0.3f,
                0.3f, 0.3f, -0.3f,
				0.3f,-0.3f,0.3f,

				//right 2
                0.3f, 0.3f, 0.3f,
                0.3f,-0.3f,0.3f,
                0.3f, 0.3f, -0.3f,

				//top 1
                -0.3f, 0.3f, -0.3f,
                -0.3f, 0.3f, 0.3f,
				0.3f,0.3f,-0.3f,

				//top 2
                0.3f, 0.3f, 0.3f,
                0.3f, 0.3f, -0.3f,
                -0.3f, 0.3f, 0.3f,

                //bottom 1
                -0.3f, -0.3f, 0.3f,
                -0.3f, -0.3f, -0.3f,
                0.3f, -0.3f, 0.3f,

				//bottom 2
                0.3f, -0.3f, -0.3f,
                0.3f, -0.3f, 0.3f,
                -0.3f, -0.3f, -0.3f,

				//front 1
				0.3f, 0.3f, 0.3f,
				-0.3f, 0.3f, 0.3f,
				-0.3f, -0.3f, 0.3f,

				//front 2
				-0.3f, -0.3f, 0.3f,
				0.3f, -0.3f, 0.3f,
                0.3f, 0.3f, 0.3f,

				//left 1
				-0.3f, 0.3f, 0.3f,
				-0.3f, -0.3f, -0.3f,
                -0.3f, -0.3f, 0.3f,

				//left 2
                -0.3f, 0.3f, -0.3f,
                -0.3f, -0.3f, -0.3f,
                -0.3f, 0.3f, 0.3f
        };

		float[] piramideFarbe = {
				1,0,0,
				0,1,0,
				0,0,1,

				1,0,0,
				0,0,1,
				0,1,0,

				1,0,0,
				0,0,1,
				0,1,0,

				1,0,0,
				0,1,0,
				0,0,1,

				0,1,0,
				1,0,0,
				0,0,1,

				1,0,0,
				0,1,0,
				0,0,1
		};

		float[] wurfelFarbe = {
				//front 1
				1,0,0,
				0,1,0,
				0,0,1,

				//front 2
				0,0,1,
				0,1,0,
				1,0,0,

				//right 1
				0,0,1,
				1,0,0,
				0,1,0,

				//right 2
				0,0,1,
				0,1,0,
				1,0,0,

				//top 1
				0,1,0,
				1,0,0,
				0,0,1,

				//top 2
				0,1,0,
				0,0,1,
				1,0,0,

				//bottom 1
				1,0,0,
				0,1,0,
				0,0,1,

				//bottom 2
				1,0,0,
				0,0,1,
				0,1,0,

				//back 1
				1,0,0,
				0,1,0,
				0,0,1,

				//back 2
				1,0,0,
				0,0,1,
				0,1,0,

				//left 1
				1,0,0,
				0,1,0,
				0,0,1,

				//left 2
				1,0,0,
				0,0,1,
				0,1,0
		};

		float[] piramidenNormalen = {
				//back
				0,1,-1,
				0,1,-1,
				0,1,-1,

		        //front
		        0,1,1,
                0,1,1,
                0,1,1,

				//left
				-1,1,0,
				-1,1,0,
				-1,1,0,

                //right
                1,1,0,
                1,1,0,
                1,1,0,

                //bottom1
                0,-1,0,
                0,-1,0,
                0,-1,0,

                //bottom2
                0,-1,0,
                0,-1,0,
                0,-1,0
        };

		float[] wurfelNormalen = {
		        //back 1
                0,0,-1,
                0,0,-1,
                0,0,-1,

                //back 2
                0,0,-1,
                0,0,-1,
                0,0,-1,

                //right 1
                1,0,0,
                1,0,0,
                1,0,0,

                //right 2
                1,0,0,
                1,0,0,
                1,0,0,

                //top 1
                0,1,0,
                0,1,0,
                0,1,0,

                //top 2
                0,1,0,
                0,1,0,
                0,1,0,

                //bottom 1
                0,-1,0,
                0,-1,0,
                0,-1,0,

                //bottom 2
                0,-1,0,
                0,-1,0,
                0,-1,0,

                //front 1
                0,0,1,
                0,0,1,
                0,0,1,

                //front 2
                0,0,1,
                0,0,1,
                0,0,1,

                //left 1
                -1,0,0,
                -1,0,0,
                -1,0,0,

                //left 2
                -1,0,0,
                -1,0,0,
                -1,0,0
        };

		float[] piramideUV = {
				//back
				0.5f,0,
				0,1,
				1, 1,

				//front
				0.5f,0,
				0,1,
				1, 1,

				//left
				0.5f,0,
				0,1,
				1, 1,

				//right
				0.5f,0,
				0,1,
				1, 1,

				//bottom left
				0,0,
				1,0,
				0,1,

				//bottom right
				1,0,
				1,1,
				0,1
		};

		float[] wurfelUV = {
				//back 1
				1, 1,
				0, 1,
				0, 0,

				//back 2
				1, 0,
				0, 0,
				0, 1,

				//right 1
				1,1,
				1,0,
				0,1,

				//right 2
				0,0,
				0,1,
				1,0,

				//top 1
				0,0,
				0,1,
				1,0,

				//top 2
				1,1,
				1,0,
				0,1,

				//bottom 1
				0,0,
				0,1,
				1,0,

				//bottom 2
				1,1,
				1,0,
				0,1,

				//front 1
				1,0,
				0,0,
				0,1,

				//front 2
				0,1,
				1,1,
				1,0,

				//left 1
				1,0,
				0,1,
				1,1,

				//left 2
				0,0,
				0,1,
				1,0

		};

		ShaderProgram shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		vao1Id = glGenVertexArrays();
		glBindVertexArray(vao1Id);

		int piramideColor = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, piramideColor);
		glBufferData(GL_ARRAY_BUFFER,
				piramideFarbe, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(1);

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		int piramideBuffers = glGenBuffers();

		glBindBuffer(GL_ARRAY_BUFFER, piramideBuffers);
		glBufferData(GL_ARRAY_BUFFER, piramiden, GL_STATIC_DRAW);
		glVertexAttribPointer(2,3, GL_FLOAT,
		false, 0, 0);
		glEnableVertexAttribArray(2);

        int piramidenNormaleVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, piramidenNormaleVBO);
        glBufferData(GL_ARRAY_BUFFER, piramidenNormalen, GL_STATIC_DRAW);
        glVertexAttribPointer(3,3, GL_FLOAT,
                false, 0, 0);
        glEnableVertexAttribArray(3);

		int piramidenUVVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, piramidenUVVBO);
		glBufferData(GL_ARRAY_BUFFER, piramideUV, GL_STATIC_DRAW);
		glVertexAttribPointer(4,2, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(4);


		vao2Id = glGenVertexArrays();
		glBindVertexArray(vao2Id);

		int wurfelColor = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, wurfelColor);
		glBufferData(GL_ARRAY_BUFFER,
				wurfelFarbe, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(1);


		int wurfelBuffers = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, wurfelBuffers);
		glBufferData(GL_ARRAY_BUFFER, wurfel, GL_STATIC_DRAW);
		glVertexAttribPointer(2,3, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(2);

		int wurfelNormaleVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, wurfelNormaleVBO);
        glBufferData(GL_ARRAY_BUFFER, wurfelNormalen, GL_STATIC_DRAW);
        glVertexAttribPointer(3,3, GL_FLOAT,
                false, 0, 0);
        glEnableVertexAttribArray(3);

		int wurfelUVVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, wurfelUVVBO);
		glBufferData(GL_ARRAY_BUFFER, wurfelUV, GL_STATIC_DRAW);
		glVertexAttribPointer(4,2, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(4);


        int loc = glGetUniformLocation(shaderProgram.getId(), "proMat");

        glUniformMatrix4fv(loc, false,
                new Matrix4(1,100).getValuesAsArray());

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren

		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		// Transformation durchf�hren (Matrix anpassen)
	}
	float w = 0;
	float w1 = 0;
	float w2 = 0;
	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		int loc = glGetUniformLocation(shaderProgram.getId(), "matrix");
		w+=0.5;
		w1+=1.25;
		w2+=2.525;

		glUniformMatrix4fv(loc, false,
				new Matrix4().translate(-1,-1,-1.5f).rotateX(w).translate(0.1f,0.1f,0).rotateY(w1).rotateZ(w2).translate(0.1f,0,0).scale(1.5f).getValuesAsArray());
		glBindVertexArray(vao1Id);
		glBindTexture(GL_TEXTURE_2D, brickTex.getId());
		glDrawArrays(GL_TRIANGLES, 0, 18);

		glUniformMatrix4fv(loc, false,
				new Matrix4().translate(1,1,-1.5f).rotateX(w).translate(0.1f,0.1f,0).rotateY(w1).rotateZ(w2).translate(0.1f,0,0).scale(1.5f).getValuesAsArray());
		glBindVertexArray(vao2Id);
		glBindTexture(GL_TEXTURE_2D, brickTexLow.getId());
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
		glDrawArrays(GL_TRIANGLES, 0, 36);

		// Matrix an Shader �bertragen
		// VAOs zeichnen
	}

	@Override
	public void destroy() {
	}
}
