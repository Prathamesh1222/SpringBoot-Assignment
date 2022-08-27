package com.project.dto.transformer;

import java.util.List;

public interface DtoTransformer <A,B>{

   public B toDto(A entity);

   public A toEntity(B dto);

   public List<B> toDtoList(List<A> entity);

   public List<A> toEntityList(List<B> dto);
}
