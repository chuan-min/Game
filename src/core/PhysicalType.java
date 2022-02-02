package core;

import org.jbox2d.common.Vec3;

public class PhysicalType {
	public static final int floor = 1;
	
	public static Vec3 getPhysicalAttr(int number){
		Vec3 vec3 = new Vec3();
		switch (number) {
		case floor:
			break;
		default:
			vec3.x = 0;
			vec3.y = 0;
			vec3.z = 0;
			break;
		}
		return vec3;
	}
}
