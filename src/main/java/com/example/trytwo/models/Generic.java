package com.example.trytwo.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
public class Generic implements Serializable, Persistable<String>
{
    @Id
    private String id;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    @Override
    public boolean isNew() {
        return isNull(this.created_at);
    }

    public Generic()
    {
        this.id = UUID.randomUUID().toString();
    }
}
