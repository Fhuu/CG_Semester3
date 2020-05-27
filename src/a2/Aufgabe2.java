package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		/**
		 * VAO can contains more than one VBO,
		 * VBO can be the shape of object, and also the color
		 * 	or in short holds information about vertices.
		 * which means VAO can be made once and with more than one VBO
		 */

		float[] ecken = {
				-0.7f, 0.7f, //top left
				-0.7f, -0.7f, // bottom left
				0.7f, -0.7f //bottom right
		};

		float[] farbe = {
				1,0,0,
				0,1,0,
				0,0,1
		};

		// folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER,
				ecken, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(0);


		int vboColor = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColor);
		glBufferData(GL_ARRAY_BUFFER,
				farbe, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,
				false, 0, 0);
		glEnableVertexAttribArray(1);


		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren
		glDrawArrays(GL_TRIANGLES, 0, 3);
		// hier vorher erzeugte VAOs zeichnen
	}

	@Override
	public void destroy() {
	}
}
