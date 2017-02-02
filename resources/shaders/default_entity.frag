#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D texture_two_d;

void main()
{
	color = texture(texture_two_d, fs_in.tc);
	if (color.w < 1.0)
		discard;
}