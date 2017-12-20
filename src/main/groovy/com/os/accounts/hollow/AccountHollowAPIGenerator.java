package com.os.accounts.hollow;

import com.netflix.hollow.api.codegen.HollowAPIGenerator;
import com.netflix.hollow.core.write.HollowWriteStateEngine;
import com.netflix.hollow.core.write.objectmapper.HollowObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class AccountHollowAPIGenerator {

    private static final Logger log = Logger.getLogger(AccountHollowAPIGenerator.class.toString());

    public static void main(String args[]) throws ClassNotFoundException, IOException {
        Class className;
        String packageName;
        if (args.length == 2) {
            className = Class.forName(args[0]);
            if (className == null) {
                throw new ClassNotFoundException("ClassName Returned Is Null");
            }
            packageName = args[1];
        } else {
            throw new ClassNotFoundException("Invalid Classname or Packagename Provided");
        }
        File projectRootFolder = findRootProjectFolder();
        AccountHollowAPIGenerator generator = new AccountHollowAPIGenerator(projectRootFolder);
        generator.generateFiles(className, packageName);
    }

    private final File projectRootFolder;

    private AccountHollowAPIGenerator(File projectRootFolder) {
        this.projectRootFolder = projectRootFolder;
    }

    private void generateFiles(Class className, String packageName) throws IOException, ClassNotFoundException {

        System.out.println("Generating hollow api files for " + className.getSimpleName() + " in package: " + packageName);
        log.info("Generating hollow api files for " + className.getSimpleName() + " in package: " + packageName);

        HollowWriteStateEngine writeEngine = new HollowWriteStateEngine();
        HollowObjectMapper mapper = new HollowObjectMapper(writeEngine);
        mapper.initializeTypeState(className);

        HollowAPIGenerator generator = new HollowAPIGenerator.Builder().withAPIClassname(className.getSimpleName() + "API")
                .withPackageName(packageName)
                .withDataModel(writeEngine)
                .build();

        String generatedApiCodeFolder = "src/main/groovy/" + packageName.replace('.', '/');
        File apiCodeFolder = findProjectFolder(generatedApiCodeFolder);

        apiCodeFolder.mkdirs();

        for (File f : apiCodeFolder.listFiles())
            f.delete();

        generator.generateFiles(apiCodeFolder);
    }

    /**
     * Find the relative project folder
     */
    private File findProjectFolder(String projectFolder) {
        File f = projectRootFolder;

        for (String s : projectFolder.split("//")) {
            f = new File(f, s);
        }

        return f;
    }

    /**
     * Attempts to find the root project folder.
     * Assumption: A file 'readme', which is in the classpath, is nested somewhere underneath the root project folder.
     */
    private static File findRootProjectFolder() {
        File f = new File(AccountHollowAPIGenerator.class.getResource("/readme").getFile());
        f = f.getParentFile();

        while (!containsBuildGradle(f)) {
            f = f.getParentFile();
        }
        return f;
    }

    /**
     * Assumption: The root project folder contains a file called 'build.gradle'
     */
    private static boolean containsBuildGradle(File f) {
        return f.listFiles((dir, name) -> name.equals("build.gradle")).length > 0;
    }


}
