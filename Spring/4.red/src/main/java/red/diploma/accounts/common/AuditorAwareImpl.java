package red.diploma.accounts.common;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    private String user;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(user);
    }

    public void setCurrentAuditor(String user) {
        this.user = user;
    }
}
