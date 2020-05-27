#version 330

//in vec2 eckenD;
in vec3 n;
in vec3 meinePosition;
in vec3 zeuch;
in vec2 uvKoord;

out vec3 outputColor;

uniform sampler2D smplr;
void main() {
    vec3 N = normalize(n);
    vec3 lichtPosition = vec3(0,0,20);
    vec3 l= normalize( lichtPosition - meinePosition);
    vec3 r = reflect(-l,N);
    vec3 v = normalize(-meinePosition);
    float licht = max (0,dot(N,l))+ pow(max(0,dot(r,v)),50);

    vec4 texel = texture(smplr, uvKoord);
    outputColor = vec3(texel) * licht;
}