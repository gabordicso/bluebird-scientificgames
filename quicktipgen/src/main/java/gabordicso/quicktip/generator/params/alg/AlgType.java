package gabordicso.quicktip.generator.params.alg;

public enum AlgType {
	ALG1(1),
	ALG2(2),
	ALG3(3);
	private final int value;
	private AlgType(int value) {
		this.value = value;
	}
	public static AlgType fromValue(int value) {
		for (AlgType algType : values()) {
			if (algType.value == value) {
				return algType;
			}
		}
		return null;
	}
}
