package junit.basic._4_rules;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Only predefined rules here, you can create your own custom
 *
 * @author Ilya Galatyuk
 */
public class RulesExample {

    // first here define as .none(), then in test .expect(class)
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testExceptionThrowing() {
        expectedException.expect(RuntimeException.class);
        throw new RuntimeException("lala");
    }


    // folder that is auto-created and destroyed after tests
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File createdFolder = folder.newFolder("newfolder");
        File createdFile = folder.newFile("myfilefile.txt");
        Assert.assertTrue(createdFile.exists());
    }
}
