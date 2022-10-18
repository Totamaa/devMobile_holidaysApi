package JavaClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Holidays_json {

    @SerializedName("datasetid")
    @Expose
    private String datasetid;
    @SerializedName("recordid")
    @Expose
    private String recordid;
    @SerializedName("fields")
    @Expose
    private Fields fields;
    @SerializedName("record_timestamp")
    @Expose
    private String recordTimestamp;

    /**
     * No args constructor for use in serialization
     *
     */
    public Holidays_json() {
    }

    /**
     *
     * @param recordid
     * @param datasetid
     * @param fields
     * @param recordTimestamp
     */
    public Holidays_json(String datasetid, String recordid, Fields fields, String recordTimestamp) {
        super();
        this.datasetid = datasetid;
        this.recordid = recordid;
        this.fields = fields;
        this.recordTimestamp = recordTimestamp;
    }

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getRecordTimestamp() {
        return recordTimestamp;
    }

    public void setRecordTimestamp(String recordTimestamp) {
        this.recordTimestamp = recordTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Holidays_json.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("datasetid");
        sb.append('=');
        sb.append(((this.datasetid == null)?"<null>":this.datasetid));
        sb.append(',');
        sb.append("recordid");
        sb.append('=');
        sb.append(((this.recordid == null)?"<null>":this.recordid));
        sb.append(',');
        sb.append("fields");
        sb.append('=');
        sb.append(((this.fields == null)?"<null>":this.fields));
        sb.append(',');
        sb.append("recordTimestamp");
        sb.append('=');
        sb.append(((this.recordTimestamp == null)?"<null>":this.recordTimestamp));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

