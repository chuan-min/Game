package actor;

public interface Physical {
	public float getDensity();
	public Physical setDensity(float density);
	public float getFriction();
	public Physical setFriction(float friction);
	public float getRestitution() ;
	public Physical setRestitution(float restitution);
	public Physical setFixedRotation(boolean f);
	public Physical setPhysicalAttr(float d,float f,float r);
	
}
