package com.dev.classmoa.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailVerification is a Querydsl query type for EmailVerification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailVerification extends EntityPathBase<EmailVerification> {

    private static final long serialVersionUID = 965004789L;

    public static final QEmailVerification emailVerification = new QEmailVerification("emailVerification");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> expiredTime = createDateTime("expiredTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> sendTime = createDateTime("sendTime", java.time.LocalDateTime.class);

    public final BooleanPath verificationCheck = createBoolean("verificationCheck");

    public final StringPath verificationCode = createString("verificationCode");

    public QEmailVerification(String variable) {
        super(EmailVerification.class, forVariable(variable));
    }

    public QEmailVerification(Path<? extends EmailVerification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailVerification(PathMetadata metadata) {
        super(EmailVerification.class, metadata);
    }

}

