package red.diploma.accounts.common;

import red.diploma.accounts.model.Auditable;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

public class EntityChecker {

    public static <T extends Auditable> T requireNonNull(T obj) {
        try {
            Objects.requireNonNull(obj.getCreatedDate());
            return obj;
        } catch (NullPointerException e) {
            throw new EntityNotFoundException();
        }
    }

    public static <T extends Auditable> T requireNonNull(T obj, String message) {
        try {
            Objects.requireNonNull(obj.getCreatedDate());
            return obj;
        } catch (NullPointerException e) {
            throw new EntityNotFoundException(message);
        }
    }
}
