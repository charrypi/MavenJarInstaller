/**
 * create at 2020年4月28日
 */
package com.bigbang.util;

import java.io.IOException;

import com.bigbang.model.JarUploadConfig;

/**
 * <b>Desc</b>: TODO Function Description. <br/>
 *
 * @author pzj <br/>
 * 
 */
public final class CmdUtil {

	private CmdUtil() {
	}

	public final static Process runInstall(JarUploadConfig config) {

		try {
			StringBuilder cmd = new StringBuilder();
			cmd.append("cmd.exe /c ");
			cmd.append("mvn install:install-file ");
			cmd.append("-DgroupId=");
			cmd.append(config.getGroupId());
			cmd.append(" ");
			cmd.append("-DartifactId=");
			cmd.append(config.getArtifactId());
			cmd.append(" ");
			cmd.append("-Dversion=");
			cmd.append(config.getVersion());
			cmd.append(" ");
			cmd.append("-Dpackaging=");
			cmd.append(config.getPackaging());
			cmd.append(" ");
			cmd.append("-Dfile=");
			cmd.append(config.getPath());
			return Runtime.getRuntime().exec(cmd.toString());
		} catch (IOException e) {
			throw new RuntimeException("the cmd of install file run error:" + e.getMessage());
		}
	}
}
