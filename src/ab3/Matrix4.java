package ab3;

//Alle Operationen �ndern das Matrixobjekt selbst und geben das eigene Matrixobjekt zur�ck
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	private static double radi = Math.acos(-1) / 180;
	double[][] mat = new double[4][4];

	public Matrix4() {
		// TODO mit der Identit�tsmatrix initialisieren
		for(int row = 0; row < 4; row++) {
			mat[row][row] = 1;
		}
	}

	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
		this.mat = copy.mat;
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzuf�gen

		this.mat[0][0] = 1;
		this.mat[1][1] = 1;
		this.mat[2][2] = ((- far - near) / (far - near));
		this.mat[2][3] = ((-2 * near * far) / (far - near));
		this.mat[3][2] = -1;
	}

	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einf�gen
		double[][] prod = new double[4][4];
		Matrix4 newMat = new Matrix4();
		for(int column = 0; column < 4; column++) {
			for(int row = 0; row < 4; row++) {
				for (int count = 0; count < 4; count++) {
					prod[column][row] += other.mat[column][count] * this.mat[count][row];
				}
			}
		}
		this.mat = prod;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.mat[3][0] = x;
		newMat.mat[3][1] = y;
		newMat.mat[3][2] = z;
		multiply(newMat);
		return this;
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichm��ige Skalierung um Faktor "uniformFactor" zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		for(int index = 0; index < 3; index++) {
			newMat.mat[index][index] = uniformFactor;
		}
		return this.multiply(newMat);
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichf�rmige Skalierung zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.mat[0][0] = sx;
		newMat.mat[1][1] = sy;
		newMat.mat[2][2] = sz;
		return this.multiply(newMat);
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzuf�gen

		Matrix4 newMat = new Matrix4();
		newMat.mat[1][1] = Math.cos(angle * radi);
		newMat.mat[1][2] = -1 * Math.sin(angle * radi);
		newMat.mat[2][1] = Math.sin(angle * radi);
		newMat.mat[2][2] = Math.cos(angle * radi);
		this.multiply(newMat);
		return this;
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.mat[0][0] = Math.cos(angle * radi);
		newMat.mat[2][0] = -1 * Math.sin(angle * radi);
		newMat.mat[0][2] = Math.sin(angle * radi);
		newMat.mat[2][2] = Math.cos(angle * radi);

		return this.multiply(newMat);
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.mat[0][0] = Math.cos(angle * radi);
		newMat.mat[0][1] = -1 * Math.sin(angle * radi);
		newMat.mat[1][0] = Math.sin(angle * radi);
		newMat.mat[1][1] = Math.cos(angle * radi);

		return this.multiply(newMat);
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gef�llt) herausgeben
		float[] values = new float[16];
		int count = 0;
		for (int column = 0; column < 4; column++) {
			for(int row = 0; row < 4; row++) {
				values[count] = (float) mat[column][row];
				count++;
			}
		}
		return values;
	}
}
