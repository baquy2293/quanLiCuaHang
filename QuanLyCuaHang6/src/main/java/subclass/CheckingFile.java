package subclass;

import java.io.File;

@FunctionalInterface
public interface CheckingFile{
	boolean check(File f);
}