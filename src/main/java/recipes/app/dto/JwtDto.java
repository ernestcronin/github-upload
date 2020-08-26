package recipes.app.dto;

public class JwtDto {

	private String jwt;
	
	public JwtDto() {}
	
	public JwtDto(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
