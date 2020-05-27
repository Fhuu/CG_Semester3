#version 330

//layout(location = 0) in vec3 ecken;
layout(location = 1) in vec3 farbe;
layout(location = 2) in vec3 eckenAusJava;
layout(location = 3) in vec3 normalVektor;
layout(location = 4) in vec2 uvKoordAusJava;

uniform mat4 matrix;
uniform mat4 proMat;

out vec3 zeuch;
out vec2 uvKoord;
out vec3 meinePosition;
out vec3 n;

void main() {
    zeuch = farbe;
    uvKoord = uvKoordAusJava;
    mat3 normalMatrix = inverse(transpose(mat3(matrix)));
    meinePosition = vec3(matrix * vec4(eckenAusJava,1.0));
    n =normalMatrix * normalVektor;

    // Pixel is 700, which means from -1 to 1 should be pixel 0 to 700.
    // If -1 to 1 is added with 1, and multiplied by 350, 2 * 350 is 700 and 1 is right at the middle.
    gl_Position = proMat * matrix * vec4(eckenAusJava, 1.0);
//    gl_Position = proMat * matrix * vec4(ecken, 1.0);
}