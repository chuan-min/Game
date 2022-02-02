package actor;

import org.jbox2d.common.Vec2;

import animation.AnimHandle;
import core.HasName;
import core.HasShape;
import core.Render;
import core.Transform;
import scene.Scene;
import script.ScriptHandle;
import trigger.TriggerHandle;

/**
 * Actor:½ÇÉ«
 */
public interface Actor extends Render,ScriptHandle,TriggerHandle,AnimHandle,HasName,HasShape,Transform{
	public Scene getScene();
	public void setScene(Scene scene);
	public void destroy();
	public Vec2 getCenter();
	public float getAngle();
	public void setAngle(float angle);
	public void setDisplay(Display display);
	public Display getDisplay();
}
