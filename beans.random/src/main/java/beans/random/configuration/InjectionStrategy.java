package beans.random.configuration;

public enum InjectionStrategy {

	/**
	 * use ony field injection regardless of field visibility modifier
	 */
	FIELD,

	/**
	 * use injection via public setters or injection on public not final fields
	 */
	SETTER

}
