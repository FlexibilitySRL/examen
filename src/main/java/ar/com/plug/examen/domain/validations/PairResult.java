package ar.com.plug.examen.domain.validations;

public final class PairResult {
	
    private final boolean valid;
    private final String leyend;
	
    public PairResult(boolean valid, String leyend) {
        this.valid = valid;
        this.leyend = leyend;
    }
    
    public boolean isValid() {
		return valid;
	}
	public String getLeyend() {
		return leyend;
	}

}
