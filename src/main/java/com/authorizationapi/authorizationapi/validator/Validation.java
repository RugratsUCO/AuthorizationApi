package uco.doo.rugrats.uconnect.api.validator;

public interface Validation<T> {
	Result execute(T data);
}
