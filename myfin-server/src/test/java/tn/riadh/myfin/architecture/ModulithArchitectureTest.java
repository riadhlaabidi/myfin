package tn.riadh.myfin.architecture;

import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.core.VerificationOptions;

import com.tngtech.archunit.lang.ArchRule;

import tn.riadh.myfin.MyFinApplication;

public class ModulithArchitectureTest {

    @Test
    public void modulesTest() {
        ArchRule onion = JMoleculesArchitectureRules.ensureOnionSimple();
        VerificationOptions options = VerificationOptions.defaults().withAdditionalVerifications(onion);
        ApplicationModules.of(MyFinApplication.class).verify(options);
    }
}
