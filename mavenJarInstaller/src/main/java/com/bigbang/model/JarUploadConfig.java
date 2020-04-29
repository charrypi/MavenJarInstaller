/**
 * create at 2020年4月28日
 */
package com.bigbang.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * <b>Desc</b>: Maven jar包上传配置参数对象 <br/>
 *
 * @author pzj <br/>
 * 
 */
public class JarUploadConfig {

	private SimpleStringProperty path;
	
	private SimpleStringProperty groupId;
	
	private SimpleStringProperty artifactId;
	
	private SimpleStringProperty version;
	
	private SimpleBooleanProperty skipTest;
	
	private SimpleStringProperty packaging;
	
	public JarUploadConfig() {
	}

	public JarUploadConfig(String path, String groupId, String artifactId,
			boolean skipTest, String version,String packaging) {
		super();
		this.path = new SimpleStringProperty(path);
		this.groupId = new SimpleStringProperty(groupId);
		this.artifactId = new SimpleStringProperty(artifactId);
		this.version = new SimpleStringProperty(version);
		this.skipTest = new SimpleBooleanProperty(skipTest);
		this.packaging = new SimpleStringProperty(packaging);
	}

	public String getPath() {
		return path.get();
	}

	public void setPath(String path) {
		if(this.path==null) {
			this.path = new SimpleStringProperty();
		}
		this.path.set(path);
	}

	public String getGroupId() {
		return groupId.get();
	}

	public void setGroupId(String groupId) {
		if(this.groupId==null) {
			this.groupId = new SimpleStringProperty();
		}
		this.groupId.set(groupId);
	}

	public String getArtifactId() {
		return artifactId.get();
	}

	public void setArtifactId(String artifactId) {
		if(this.artifactId==null) {
			this.artifactId = new SimpleStringProperty();
		}
		this.artifactId.set(artifactId);
	}

	public String getVersion() {
		return version.get();
	}

	public void setVersion(String version) {
		if(this.version==null) {
			this.version = new SimpleStringProperty();
		}
		this.version.set(version);
	}

	public boolean getSkipTest() {
		return skipTest.get();
	}

	public void setSkipTest(boolean skipTest) {
		if(this.skipTest==null) {
			this.skipTest = new SimpleBooleanProperty();
		}
		this.skipTest.set(skipTest);
	}

	public String getPackaging() {
		return packaging.get();
	}

	public void setPackaging(String packaging) {
		if(this.packaging==null) {
			this.packaging = new SimpleStringProperty();
		}
		this.packaging.set(packaging);
	}
	
	
}
