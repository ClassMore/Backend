package com.dev.classmoa.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestLecture is a Querydsl query type for InterestLecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestLecture extends EntityPathBase<InterestLecture> {

    private static final long serialVersionUID = 2048150674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestLecture interestLecture = new QInterestLecture("interestLecture");

    public final BooleanPath canceled = createBoolean("canceled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLecture lecture;

    public final QMember member;

    public QInterestLecture(String variable) {
        this(InterestLecture.class, forVariable(variable), INITS);
    }

    public QInterestLecture(Path<? extends InterestLecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestLecture(PathMetadata metadata, PathInits inits) {
        this(InterestLecture.class, metadata, inits);
    }

    public QInterestLecture(Class<? extends InterestLecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

