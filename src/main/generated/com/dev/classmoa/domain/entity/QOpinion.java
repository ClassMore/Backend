package com.dev.classmoa.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOpinion is a Querydsl query type for Opinion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpinion extends EntityPathBase<Opinion> {

    private static final long serialVersionUID = -909114592L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOpinion opinion = new QOpinion("opinion");

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isModified = createBoolean("isModified");

    public final QLecture lecture;

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> writeDate = createDateTime("writeDate", java.time.LocalDateTime.class);

    public QOpinion(String variable) {
        this(Opinion.class, forVariable(variable), INITS);
    }

    public QOpinion(Path<? extends Opinion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOpinion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOpinion(PathMetadata metadata, PathInits inits) {
        this(Opinion.class, metadata, inits);
    }

    public QOpinion(Class<? extends Opinion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

