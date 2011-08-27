package net.sue445.s3tiger.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2011-08-27 17:12:25")
/** */
public final class CipherModelMeta extends org.slim3.datastore.ModelMeta<net.sue445.s3tiger.model.CipherModel> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.sue445.s3tiger.model.CipherModel> cipherString = new org.slim3.datastore.StringAttributeMeta<net.sue445.s3tiger.model.CipherModel>(this, "cipherString", "cipherString");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.sue445.s3tiger.model.CipherModel, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.sue445.s3tiger.model.CipherModel, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.sue445.s3tiger.model.CipherModel, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<net.sue445.s3tiger.model.CipherModel, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final CipherModelMeta slim3_singleton = new CipherModelMeta();

    /**
     * @return the singleton
     */
    public static CipherModelMeta get() {
       return slim3_singleton;
    }

    /** */
    public CipherModelMeta() {
        super("CipherModel", net.sue445.s3tiger.model.CipherModel.class);
    }

    @Override
    public net.sue445.s3tiger.model.CipherModel entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.sue445.s3tiger.model.CipherModel model = new net.sue445.s3tiger.model.CipherModel();
        model.setCipherString(decrypt((java.lang.String)entity.getProperty("cipherString")));
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("cipherString", encrypt(m.getCipherString()));
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        if ("cipherString".equals(propertyName)) return true;
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        net.sue445.s3tiger.model.CipherModel m = (net.sue445.s3tiger.model.CipherModel) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCipherString() != null){
            writer.setNextPropertyName("cipherString");
            encoder0.encode(writer, encrypt(m.getCipherString()));
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected net.sue445.s3tiger.model.CipherModel jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        net.sue445.s3tiger.model.CipherModel m = new net.sue445.s3tiger.model.CipherModel();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("cipherString");
        if(reader.read() != null){
            reader = new org.slim3.datastore.json.JsonValueReader(decrypt(reader.read()), rootReader.getModelReader());
        }
        m.setCipherString(decoder0.decode(reader, m.getCipherString()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}