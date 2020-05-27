#version 330

in vec3 zeuch;
in vec2 eckenD;
out vec3 outputColor;


void createRect(int left, int top, int right, int bottom) {
    if(eckenD.x  > left && eckenD.x < right &&
    eckenD.y > top && eckenD.y < bottom) {
        outputColor = vec3(1.0, 0.0, 0.0);
    }
}

bool istImKreis(int centerX, int centerY, int radius) {

    float diffX = distance(centerX, gl_FragCoord.x);
    float diffY = distance(centerY, gl_FragCoord.y);

    float pointDistance = sqrt((diffX * diffX) + (diffY * diffY));

    if(pointDistance <= radius) {
        return true;
    }
    return false;
}

mat2 rotate2d(float angle) {
    float pi = acos(-1);
    float angleRad = angle * pi / 180;
    mat2 rotationMat = mat2 (cos(angleRad), sin(angleRad), -sin(angleRad), cos(angleRad));
    return rotationMat;
}


void main() {
    outputColor = zeuch;

    if(istImKreis(350,350, 100)) {
        outputColor = vec3(0.0,1.0,1.0);
    }

    int startX = 325;
    int wX = 50;
    int startY = 325;
    int hY = 50;

    if(eckenD.x  > (startX) && eckenD.x < (startX + wX) &&
    eckenD.y > (startY) && eckenD.y < (startY + hY)) {
        outputColor = vec3(1.0, 0.0, 0.0);
    }

    vec2 rotiert = vec2(rotate2d(45) * vec2(eckenD.x - startX - 25, eckenD.y - startY - 25));


    if(rotiert.x  > (startX - startX - 25) && rotiert.x < (startX - startX - 25 + wX) &&
    rotiert.y > (startY - startY - 25) && rotiert.y < (startY - startY + hY - 25)) {
        outputColor = vec3(1.0, 1.0, 0.0);
    }
}