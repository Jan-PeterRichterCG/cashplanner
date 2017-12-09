package eu.jrichter.cashplanner.general.common;

import org.springframework.boot.test.context.SpringBootTest;

import eu.jrichter.cashplanner.SpringBootApp;
import io.oasp.module.test.common.base.ComponentTest;

/**
 * Abstract class to derive a {@link ComponentTest} from that needs the annotation @SpringBootTest(classes =
 * SpringBootApp.class)
 *
 * @author jrichter
 * @since dev
 */

@SpringBootTest(classes = SpringBootApp.class)
public abstract class AbstractApplicationComponentTest extends ComponentTest {

}
