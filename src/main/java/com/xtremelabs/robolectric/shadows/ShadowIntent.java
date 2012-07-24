package com.xtremelabs.robolectric.shadows;

import static android.content.Intent.ACTION_VIEW;
import static android.content.Intent.URI_INTENT_SCHEME;
import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;
import com.xtremelabs.robolectric.internal.RealObject;
import com.xtremelabs.robolectric.util.Join;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

@SuppressWarnings({"UnusedDeclaration"})
@Implements(Intent.class)
public class ShadowIntent {
    @RealObject private Intent realIntent;

    private HashMap<String, Object> extras = new HashMap<String, Object>();
    private String action;
    private ComponentName componentName;
    private String type;
    private Uri data;
    private int flags;
    private Class<?> intentClass;

    @Implementation
    public static Intent createChooser(Intent target, CharSequence title) {
        Intent intent = new Intent(Intent.ACTION_CHOOSER);
        intent.putExtra(Intent.EXTRA_INTENT, target);
        if (title != null) {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        return intent;
    }

    public void __constructor__(Context packageContext, Class cls) {
        componentName = new ComponentName(packageContext, cls);
    }

    public void __constructor__(String action, Uri uri) {
        this.action = action;
        data = uri;
    }

    public void __constructor__(String action) {
        __constructor__(action, null);
    }

    @Implementation
    public Intent setAction(String action) {
        this.action = action;
        return realIntent;
    }

    @Implementation
    public String getAction() {
        return action;
    }

    @Implementation
    public Intent setType(String type) {
        this.type = type;
        return realIntent;
    }

    @Implementation
    public String getType() {
        return type;
    }

    @Implementation
    public Uri getData() {
        return data;
    }

    @Implementation
    public Intent setClass(Context packageContext, Class<?> cls) {
        this.intentClass = cls;
        return realIntent;
    }

    @Implementation
    public Intent setClassName(String packageName, String className) {
        componentName = new ComponentName(packageName, className);
        return realIntent;
    }

    @Implementation
    public Intent setData(Uri data) {
        this.data = data;
        return realIntent;
    }

    @Implementation
    public int getFlags() {
        return flags;
    }

    @Implementation
    public Intent setFlags(int flags) {
        this.flags = flags;
        return realIntent;
    }

    @Implementation
    public Intent putExtras(Bundle src) {
        ShadowBundle srcShadowBundle = Robolectric.shadowOf_(src);
        extras = new HashMap<String, Object>(srcShadowBundle.map);
        return realIntent;
    }
    
    @Implementation
    public Intent putExtras(Intent src) {
        ShadowIntent srcShadowIntent = shadowOf(src);
        extras = new HashMap<String, Object>(srcShadowIntent.extras);
        return realIntent;
    }

    @Implementation
    public Bundle getExtras() {
        Bundle bundle = new Bundle();
        ((ShadowBundle) Robolectric.shadowOf_(bundle)).map.putAll(extras);
        return bundle;
    }
    
    @Implementation
    public Intent putExtra(String key, int value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, long value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, double value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, Serializable value) {
        extras.put(key, serializeCycle(value));
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, Parcelable value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, String value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, String[] value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public Intent putExtra(String key, boolean value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public boolean getBooleanExtra(String name, boolean defaultValue) {
        return extras.containsKey(name) ? (Boolean) extras.get(name) : defaultValue;
    }

    @Implementation
    public String[] getStringArrayExtra(String name) {
        return (String[]) extras.get(name);
    }

    @Implementation
    public Intent putExtra(String key, CharSequence value) {
        extras.put(key, value);
        return realIntent;
    }

    @Implementation
    public void putExtra(String key, byte[] value) {
        extras.put(key, value);
    }
    
    @Implementation
    public Intent putParcelableArrayListExtra(String key, ArrayList<Parcelable> value) {
    	extras.put(key, value );
    	return realIntent;
    }

    @Implementation
    public ArrayList<Parcelable> getParcelableArrayListExtra(String key) {
    	return (ArrayList<Parcelable>) extras.get(key);
    }
    
    @Implementation
    public boolean hasExtra(String name) {
	    return extras.containsKey(name);
	}

	@Implementation
    public String getStringExtra(String name) {
        return (String) extras.get(name);
    }

    @Implementation
    public Parcelable getParcelableExtra(String name) {
        return (Parcelable) extras.get(name);
    }

    @Implementation
    public int getIntExtra(String name, int defaultValue) {
        Integer foundValue = (Integer) extras.get(name);
        return foundValue == null ? defaultValue : foundValue;
    }

    @Implementation
    public long getLongExtra(String name, long defaultValue) {
        Long foundValue = (Long) extras.get(name);
        return foundValue == null ? defaultValue : foundValue;
    }

    @Implementation
    public double getDoubleExtra(String name, double defaultValue) {
        Double foundValue = (Double) extras.get(name);
        return foundValue == null ? defaultValue : foundValue;
    }

    @Implementation
    public byte[] getByteArrayExtra(String name) {
        return (byte[]) extras.get(name);
    }

    @Implementation
    public Serializable getSerializableExtra(String name) {
        return (Serializable) extras.get(name);
    }
    
    @Implementation
    public void removeExtra(String name) {
    	extras.remove(name);
    }

    @Implementation
    public Intent setComponent(ComponentName componentName) {
        this.componentName = componentName;
        return realIntent;
    }

    @Implementation
    public ComponentName getComponent() {
        return componentName;
    }

    /**
     * Compares an {@code Intent} with a {@code ShadowIntent} (obtained via a call to
     * {@link Robolectric#shadowOf(android.content.Intent)})
     *
     * @param o a {@code ShadowIntent}
     * @return whether they are equivalent
     */
    @Deprecated
    public boolean realIntentEquals(ShadowIntent o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        if (action != null ? !action.equals(o.action) : o.action != null) return false;
        if (componentName != null ? !componentName.equals(o.componentName) : o.componentName != null)
            return false;
        if (data != null ? !data.equals(o.data) : o.data != null) return false;
        if (extras != null ? !extras.equals(o.extras) : o.extras != null) return false;
        if (type != null ? !type.equals(o.type) : o.type != null) return false;

        return true;
    }

    @Override @Implementation
    public int hashCode() {
        int result = extras != null ? extras.hashCode() : 0;
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + flags;
        return result;
    }

    @Override @Implementation
    public boolean equals(Object o) {
        if (!(o instanceof Intent)) return false;
        return realIntentEquals(shadowOf((Intent) o));
    }

    /**
     * Non-Android accessor that returns the {@code Class} object set by
     * {@link #setClass(android.content.Context, Class)}
     *
     * @return the {@code Class} object set by
     *         {@link #setClass(android.content.Context, Class)}
     */
    public Class<?> getIntentClass() {
        return intentClass;
    }

    @Override @Implementation
    public String toString() {
        return "Intent{" +
                Join.join(
                        ", ",
                        ifWeHave(componentName, "componentName"),
                        ifWeHave(action, "action"),
                        ifWeHave(extras, "extras"),
                        ifWeHave(data, "data"),
                        ifWeHave(type, "type")
                ) +
                '}';
    }

    private Serializable serializeCycle(Serializable serializable) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(byteArrayOutputStream);
            output.writeObject(serializable);
            output.close();

            byte[] bytes = byteArrayOutputStream.toByteArray();
            ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (Serializable) input.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String ifWeHave(Object o, String name) {
        if (o == null) return null;
        if (o instanceof Map && ((Map) o).isEmpty()) return null;
        return name + "=" + o;
    }

    /**
     * Create an intent from a URI.  This URI may encode the action,
     * category, and other intent fields, if it was returned by
     * {@link #toUri}.  If the Intent was not generate by toUri(), its data
     * will be the entire URI and its action will be ACTION_VIEW.
     *
     * <p>The URI given here must not be relative -- that is, it must include
     * the scheme and full path.
     *
     * @param uri The URI to turn into an Intent.
     * @param flags Additional processing flags.  Either 0 or
     * {@link Intent#URI_INTENT_SCHEME}.
     *
     * @return Intent The newly created Intent object.
     *
     * @throws URISyntaxException Throws URISyntaxError if the basic URI syntax
     * it bad (as parsed by the Uri class) or the Intent data within the
     * URI is invalid.
     *
     * @see #toUri
     */
	    @Implementation
    public static Intent parseUri(String uri, int flags) throws URISyntaxException {
        int i = 0;
        try {
            // Validate intent scheme for if requested.
            if ((flags& URI_INTENT_SCHEME) != 0) {
                if (!uri.startsWith("intent:")) {
                    Intent intent = new Intent(ACTION_VIEW);
                    try {
                        intent.setData(Uri.parse(uri));
                    } catch (IllegalArgumentException e) {
                        throw new URISyntaxException(uri, e.getMessage());
                    }
                    return intent;
                }
            }

            // simple case
            i = uri.lastIndexOf("#");
            if (i == -1) return new Intent(ACTION_VIEW, Uri.parse(uri));

            // old format Intent URI
            if (!uri.startsWith("#Intent;", i)) throw new UnsupportedOperationException("Mock does not support old intents");

            // new format
            Intent intent = new Intent(ACTION_VIEW);
            final ShadowIntent shadowIntent = Robolectric.shadowOf(intent);
            Intent baseIntent = intent;

            // fetch data part, if present
            String data = i >= 0 ? uri.substring(0, i) : null;
            String scheme = null;
            i += "#Intent;".length();

            // loop over contents of Intent, all name=value;
            while (!uri.startsWith("end", i)) {
                int eq = uri.indexOf('=', i);
                if (eq < 0) eq = i-1;
                int semi = uri.indexOf(';', i);
                String value = eq < semi ? Uri.decode(uri.substring(eq + 1, semi)) : "";

                // action
                if (uri.startsWith("action=", i)) {
                    intent.setAction(value);
                }

                // categories
                else if (uri.startsWith("category=", i)) {
                    intent.addCategory(value);
                }

                // type
                else if (uri.startsWith("type=", i)) {
                    shadowIntent.type = value;
                }

                // launch flags
                else if (uri.startsWith("launchFlags=", i)) {
                    shadowIntent.flags = Integer.decode(value).intValue();
                }

                // package
                else if (uri.startsWith("package=", i)) {
                    intent.setPackage(value);
                }

                // component
                else if (uri.startsWith("component=", i)) {
                    intent.setComponent(ComponentName.unflattenFromString(value));
                }

                // scheme
                else if (uri.startsWith("scheme=", i)) {
                    scheme = value;
                }

                // source bounds
                else if (uri.startsWith("sourceBounds=", i)) {
                    intent.setSourceBounds(android.graphics.Rect.unflattenFromString(value));
                }

                // selector
                else if (semi == (i+3) && uri.startsWith("SEL", i)) {
                    intent = new Intent();
                }

                // extra
                else {
                    String key = Uri.decode(uri.substring(i + 2, eq));
                    // create Bundle if it doesn't already exist
                    if (shadowIntent.extras == null) shadowIntent.extras = new HashMap<String, Object>();
                    Map<String, Object> b = shadowIntent.extras;
                    // add EXTRA
                    if      (uri.startsWith("S.", i)) b.put(key, value);
                    else if (uri.startsWith("B.", i)) b.put(key, Boolean.parseBoolean(value));
                    else if (uri.startsWith("b.", i)) b.put(key, Byte.parseByte(value));
                    else if (uri.startsWith("c.", i)) b.put(key, value.charAt(0));
                    else if (uri.startsWith("d.", i)) b.put(key, Double.parseDouble(value));
                    else if (uri.startsWith("f.", i)) b.put(key, Float.parseFloat(value));
                    else if (uri.startsWith("i.", i)) b.put(key, Integer.parseInt(value));
                    else if (uri.startsWith("l.", i)) b.put(key, Long.parseLong(value));
                    else if (uri.startsWith("s.", i)) b.put(key, Short.parseShort(value));
                    else throw new URISyntaxException(uri, "unknown EXTRA type", i);
                }

                // move to the next item
                i = semi + 1;
            }

            if (intent != baseIntent) {
                // The Intent had a selector; fix it up.
                // baseIntent.setSelector(intent);
                // intent = baseIntent;
                throw new UnsupportedOperationException("Robolectric does not support selectors currently");
            }

            if (data != null) {
                if (data.startsWith("intent:")) {
                    data = data.substring(7);
                    if (scheme != null) {
                        data = scheme + ':' + data;
                    }
                }

                if (data.length() > 0) {
                    try {
                        shadowIntent.data = Uri.parse(data);
                    } catch (IllegalArgumentException e) {
                        throw new URISyntaxException(uri, e.getMessage());
                    }
                }
            }

            return intent;

        } catch (IndexOutOfBoundsException e) {
            throw new URISyntaxException(uri, "illegal Intent URI format", i);
        }
    }

    /**
     * Call {@link #toUri} with 0 flags.
     * @deprecated Use {@link #toUri} instead.
     */
    @Deprecated
    @Implementation
    public String toURI() {
        return toUri(0);
    }

    /**
     * Convert this Intent into a String holding a URI representation of it.
     * The returned URI string has been properly URI encoded, so it can be
     * used with {@link Uri#parse Uri.parse(String)}.  The URI contains the
     * Intent's data as the base URI, with an additional fragment describing
     * the action, categories, type, flags, package, component, and extras.
     *
     * <p>You can convert the returned string back to an Intent with
     * {@link Intent#getIntent}.
     *
     * @param flags Additional operating flags.  Either 0 or
     * {@link Intent#URI_INTENT_SCHEME}.
     *
     * @return Returns a URI encoding URI string describing the entire contents
     * of the Intent.
     */
    @Implementation
    public String toUri(int flags) {
        StringBuilder uri = new StringBuilder(128);
        String scheme = null;
        if (data != null) {
            String sdata = data.toString();
            if ((flags& URI_INTENT_SCHEME) != 0) {
                final int N = sdata.length();
                for (int i=0; i<N; i++) {
                    char c = sdata.charAt(i);
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
                            || c == '.' || c == '-') {
                        continue;
                    }
                    if (c == ':' && i > 0) {
                        // Valid scheme.
                        scheme = sdata.substring(0, i);
                        uri.append("intent:");
                        sdata = sdata.substring(i+1);
                        break;
                    }

                    // No scheme.
                    break;
                }
            }
            uri.append(sdata);

        } else if ((flags& URI_INTENT_SCHEME) != 0) {
            uri.append("intent:");
        }

        uri.append("#Intent;");

        toUriInner(uri, scheme, flags);
        //if (mSelector != null) {
            //uri.append("SEL;");
            // Note that for now we are not going to try to handle the
            // data part; not clear how to represent this as a URI, and
            // not much utility in it.
            //mSelector.toUriInner(uri, null, flags);
        //}

        uri.append("end");

        return uri.toString();
    }

    private void toUriInner(StringBuilder uri, String scheme, int flags) {
        if (scheme != null) {
            uri.append("scheme=").append(scheme).append(';');
        }
        if (action != null) {
            uri.append("action=").append(Uri.encode(action)).append(';');
        }
//        if (mCategories != null) {
//            for (String category : mCategories) {
//                uri.append("category=").append(Uri.encode(category)).append(';');
//            }
//        }
        if (type != null) {
            uri.append("type=").append(Uri.encode(type, "/")).append(';');
        }
        if (flags != 0) {
            uri.append("launchFlags=0x").append(Integer.toHexString(flags)).append(';');
        }
//        if (mPackage != null) {
//            uri.append("package=").append(Uri.encode(mPackage)).append(';');
//        }
        if (componentName != null) {
            uri.append("component=").append(Uri.encode(
                    componentName.flattenToShortString(), "/")).append(';');
        }
//        if (mSourceBounds != null) {
//            uri.append("sourceBounds=")
//                    .append(Uri.encode(mSourceBounds.flattenToString()))
//                    .append(';');
//        }
        if (extras != null) {
            for (String key : extras.keySet()) {
                final Object value = extras.get(key);
                char entryType =
                        value instanceof String    ? 'S' :
                        value instanceof Boolean   ? 'B' :
                        value instanceof Byte      ? 'b' :
                        value instanceof Character ? 'c' :
                        value instanceof Double    ? 'd' :
                        value instanceof Float     ? 'f' :
                        value instanceof Integer   ? 'i' :
                        value instanceof Long      ? 'l' :
                        value instanceof Short     ? 's' :
                        '\0';

                if (entryType != '\0') {
                    uri.append(entryType);
                    uri.append('.');
                    uri.append(Uri.encode(key));
                    uri.append('=');
                    uri.append(Uri.encode(value.toString()));
                    uri.append(';');
                }
            }
        }
    }

}
