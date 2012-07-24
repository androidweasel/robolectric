package com.xtremelabs.robolectric.shadows;

import android.content.ComponentName;
import android.content.Context;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

import static com.xtremelabs.robolectric.Robolectric.shadowOf_;

/**
 * Shadows the {@code android.content.ComponentName} class.
 * <p/>
 * Just keeps track of the package and class names, and then gives them back when you ask for them.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Implements(ComponentName.class)
public class ShadowComponentName {
    private String pkg;
    private String cls;

    public void __constructor__(String pkg, String cls) {
        if (pkg == null) throw new NullPointerException("package name is null");
        if (cls == null) throw new NullPointerException("class name is null");
        this.pkg = pkg;
        this.cls = cls;
    }

    public void __constructor__(Context pkg, String cls) {
        if (cls == null) throw new NullPointerException("class name is null");
        this.pkg = pkg.getPackageName();
        this.cls = cls;
    }

    public void __constructor__(Context pkg, Class<?> cls) {
        this.pkg = pkg.getPackageName();
        this.cls = cls.getName();
    }

    @Implementation
    public String getPackageName() {
        return pkg;
    }

    @Implementation
    public String getClassName() {
        return cls;
    }

    /**
     * Return the class name, either fully qualified or in a shortened form
     * (with a leading '.') if it is a suffix of the package.
     */
    @Implementation
    public String getShortClassName() {
        if (cls.startsWith(pkg)) {
            int PN = pkg.length();
            int CN = cls.length();
            if (CN > PN && cls.charAt(PN) == '.') {
                return cls.substring(PN, CN);
            }
        }
        return cls;
    }

    @Override @Implementation
    public boolean equals(Object o) {
        if (o == null) return false;
        o = shadowOf_(o);
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        ShadowComponentName that = (ShadowComponentName) o;

        if (cls != null ? !cls.equals(that.cls) : that.cls != null) return false;
        if (pkg != null ? !pkg.equals(that.pkg) : that.pkg != null) return false;

        return true;
    }

    @Override @Implementation
    public int hashCode() {
        int result = pkg != null ? pkg.hashCode() : 0;
        result = 31 * result + (cls != null ? cls.hashCode() : 0);
        return result;
    }

    @Override @Implementation
    public String toString() {
        return "ComponentName{" +
                "pkg='" + pkg + '\'' +
                ", cls='" + cls + '\'' +
                '}';
    }

    /**
     * Return a String that unambiguously describes both the package and
     * class names contained in the ComponentName.  You can later recover
     * the ComponentName from this string through
     * {@link #unflattenFromString(String)}.
     *
     * @return Returns a new String holding the package and class names.  This
     * is represented as the package name, concatenated with a '/' and then the
     * class name.
     *
     * @see #unflattenFromString(String)
     */
    @Implementation
    public String flattenToString() {
        return pkg + "/" + cls;
    }

	/**
	 * The same as {@link #flattenToString()}, but abbreviates the class
	 * name if it is a suffix of the package.  The result can still be used
	 * with {@link #unflattenFromString(String)}.
	 *
	 * @return Returns a new String holding the package and class names.  This
	 * is represented as the package name, concatenated with a '/' and then the
	 * class name.
	 *
	 * @see #unflattenFromString(String)
	 */
	@Implementation
	public String flattenToShortString() {
		return pkg + "/" + getShortClassName();
	}
   /**
     * Recover a ComponentName from a String that was previously created with
     * {@link #flattenToString()}.  It splits the string at the first '/',
     * taking the part before as the package name and the part after as the
     * class name.  As a special convenience (to use, for example, when
     * parsing component names on the command line), if the '/' is immediately
     * followed by a '.' then the final class name will be the concatenation
     * of the package name with the string following the '/'.  Thus
     * "com.foo/.Blah" becomes package="com.foo" class="com.foo.Blah".
     *
     * @param str The String that was returned by flattenToString().
     * @return Returns a new ComponentName containing the package and class
     * names that were encoded in <var>str</var>
     *
     * @see #flattenToString()
     */
    @Implementation
    public static ComponentName unflattenFromString(String str) {
        int sep = str.indexOf('/');
        if (sep < 0 || (sep+1) >= str.length()) {
            return null;
        }
        String pkg = str.substring(0, sep);
        String cls = str.substring(sep+1);
        if (cls.length() > 0 && cls.charAt(0) == '.') {
            cls = pkg + cls;
        }
        return new ComponentName(pkg, cls);
    }
}
