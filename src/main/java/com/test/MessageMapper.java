package com.test;

import org.mapstruct.*;

@Mapper(implementationName = "DefaultMessageMapper", uses = { MappingUtils.class })
public interface MessageMapper {
    @Mapping(
            source = "json",
            target = "data",
            qualifiedBy = MappingUtils.JsonToObject.class,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    Message toMessage(final MessageDTO entity);

    @Mapping(
            source = "data",
            target = "json",
            qualifiedBy = MappingUtils.ObjectToJson.class,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
    )
    MessageDTO toDTO(final Message entity);
}
