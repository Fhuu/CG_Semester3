#version 330

layout(location = 0) in vec2 ecken;
layout(location = 1) in vec3 farbe;
out vec3 zeuch;
// Works the same way as color
out vec2 eckenD;

mat2 rotationMat(float angle) {
    float pi = acos(-1);
    float angleRad = angle * pi / 180;
    return mat2 (cos(angleRad), sin(angleRad), -sin(angleRad), cos(angleRad));
}

void main() {
    // Pixel is 700, which means from -1 to 1 should be pixel 0 to 700.
    // If -1 to 1 is added with 1, and multiplied by 350, 2 * 350 is 700 and 1 is right at the middle.
    eckenD = (ecken+vec2(1))*350;

    zeuch = farbe;
    vec2 rotiert = vec2(rotationMat(200) * ecken );
    gl_Position = vec4(rotiert, 0.0, 1.0);
}