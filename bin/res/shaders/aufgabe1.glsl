#version 330
#define PI 3.1415926535897932384626433832795

out vec3 pixelFarbe;

bool istImKreis(int centerX, int centerY, int radius) {

    float diffX = distance(centerX, gl_FragCoord.x);
    float diffY = distance(centerY, gl_FragCoord.y);

    float pointDistance = sqrt((diffX * diffX) + (diffY * diffY));

    if(pointDistance <= radius) {
        return true;
    }
    return false;
}

mat2 rotate2d(float angle){
    float angleRad = angle/PI;
    return mat2(cos(angleRad),-sin(angleRad),
    sin(angleRad),cos(angleRad));
}

void main() {

    pixelFarbe = vec3(0.0,0.5,0.7);


    if(istImKreis(350,350, 100)) {
        pixelFarbe = vec3(0.0,1.0,1.0);
    }

    if(istImKreis(460,90, 50)) {
        pixelFarbe = vec3(0.0,1.0,1.0);
    }

    int centerX = 350;
    int centerY = 350;
    int radius = 100;

    float diffX = distance(centerX, gl_FragCoord.x);
    float diffY = distance(centerY, gl_FragCoord.y);

    float distance = sqrt(diffX * diffX + diffY * diffY);



    int[4] limitCoord1;
    limitCoord1[0] = 470;
    limitCoord1[1] = 590;
    limitCoord1[2] = 370;
    limitCoord1[3] = 560;

    int[4] limitCoord2;
    limitCoord2[0] = 10;
    limitCoord2[1] = 50;
    limitCoord2[2] = 30;
    limitCoord2[3] = 40;

    if(gl_FragCoord.x  > limitCoord1[0] && gl_FragCoord.x < limitCoord1[1] &&
    gl_FragCoord.y > limitCoord1[2] && gl_FragCoord.y < limitCoord1[3]) {
        pixelFarbe = vec3(1.0, 0.0, 0.0);
    }

    if(gl_FragCoord.x  > limitCoord2[0] && gl_FragCoord.x < limitCoord2[1] &&
    gl_FragCoord.y > limitCoord2[2] && gl_FragCoord.y < limitCoord2[3]) {
        pixelFarbe = vec3(1.0, 0.0, 0.0);
    }

    int[4] limitRotate1;
    limitRotate1[0] = limitCoord1[0] - limitCoord1[0];
    limitRotate1[1] = limitCoord1[1] - limitCoord1[0];
    limitRotate1[2] = limitCoord1[2] - limitCoord1[2];
    limitRotate1[3] = limitCoord1[3] - limitCoord1[2];

    float checkX = gl_FragCoord.x - limitCoord1[0];
    float checkY = gl_FragCoord.y - limitCoord1[2];

    vec2 checkXY = vec2(checkX, checkY);
    checkXY = checkXY * rotate2d(10);

    if(checkXY.x >= limitRotate1[0] && checkXY.x <= limitRotate1[1]&&
    checkXY.y >= limitRotate1[3] && checkXY.y <= limitRotate1[3]) {
        pixelFarbe = vec3(0,0,0);
    }
}