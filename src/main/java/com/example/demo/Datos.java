package com.example.demo;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "datos")
//parameters,OSname,OSversion,Brower,BrowserVersion
public class Datos {
    @Id
    public String id;
    @Field(type = FieldType.Text,name = "parametro")
    public String parametro;
    @Field(type = FieldType.Text,name = "OSname")
    public String OSname;
    @Field(type = FieldType.Text,name = "OSversion")
    public String OSversion;
    @Field(type = FieldType.Text,name = "Browser")
    public String Browser;
    @Field(type = FieldType.Text,name = "BrowserVersion")
    public String BrowserVersion;
}
