package com.dev.classmoa.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSocialLoginPlatform is a Querydsl query type for SocialLoginPlatform
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSocialLoginPlatform extends EntityPathBase<SocialLoginPlatform> {

    private static final long serialVersionUID = -1999280339L;

    public static final QSocialLoginPlatform socialLoginPlatform = new QSocialLoginPlatform("socialLoginPlatform");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath platformName = createString("platformName");

    public QSocialLoginPlatform(String variable) {
        super(SocialLoginPlatform.class, forVariable(variable));
    }

    public QSocialLoginPlatform(Path<? extends SocialLoginPlatform> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSocialLoginPlatform(PathMetadata metadata) {
        super(SocialLoginPlatform.class, metadata);
    }

}

