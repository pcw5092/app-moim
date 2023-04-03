package data;

public class User {
	String Id;
	String Pass;
	String Name;
	String AvatarId;
	String AvatarURL;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAvatarId() {
		return AvatarId;
	}

	public void setAvatarId(String avatarId) {
		AvatarId = avatarId;
	}

	public String getAvatarURL() {
		return AvatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		AvatarURL = avatarURL;
	}

}
