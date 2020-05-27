#version 330
#define PI 3.1415926535897932384626433832795

out vec3 pixelFarbe;

void createRect(int left, int top, int right, int bottom) {
    if(gl_FragCoord.x  > left && gl_FragCoord.x < right &&
    gl_FragCoord.y > top && gl_FragCoord.y < bottom) {
        pixelFarbe = vec3(1.0, 0.0, 0.0);
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
    return mat2 (cos(angleRad), -sin(angleRad), sin(angleRad), cos(angleRad));
}

void main() {

    pixelFarbe = vec3(0.0,0.5,0.7);


    if(istImKreis(350,350, 100)) {
        pixelFarbe = vec3(0.0,1.0,1.0);
    }

    if(istImKreis(460,90, 50)) {
        pixelFarbe = vec3(0.0,1.0,1.0);
    }

    int startX = 350;
    int wX = 50;
    int startY = 350;
    int hY = 50;

    if(gl_FragCoord.x  > (startX) && gl_FragCoord.x < (startX + wX) &&
    gl_FragCoord.y > (startY) && gl_FragCoord.y < (startY + hY)) {
        pixelFarbe = vec3(1.0, 0.0, 0.0);
    }

    vec2 rotiert = vec2(rotate2d(45) * vec2(gl_FragCoord.x - startX - 25, gl_FragCoord.y - startY - 25));


    if(rotiert.x  > (startX - startX - 25) && rotiert.x < (startX - startX - 25 + wX) &&
    rotiert.y > (startY - startY - 25) && rotiert.y < (startY - startY + hY - 25)) {
        pixelFarbe = vec3(1.0, 1.0, 0.0);
    }

}