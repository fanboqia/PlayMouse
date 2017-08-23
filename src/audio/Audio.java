package audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Audio {
	public Audio() {
		File file = new File("music/hit.wav"); // 获取文件，传入的参数为String类型，文件的路径
		java.net.URL url = null;
		try {
			url = file.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} // 获取文件的路径
		AudioClip ac = Applet.newAudioClip(url); // 因为AudioClip是接口不能实例化，所有用Applet中的newAduioClip来实例化
		ac.play(); // 播放
	}
}
