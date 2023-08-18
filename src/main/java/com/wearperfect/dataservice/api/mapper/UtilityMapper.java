package com.wearperfect.dataservice.api.mapper;

import java.time.Instant;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface UtilityMapper {

	@Named(value = "dateToTimeConverter")
	default Long dateToTimeConverter(Date date) {
		if (null != date) {
			return date.getTime();
		} else {
			return null;
		}
	}
	
	@Named(value = "timeToDateConverter")
	default Date timeToDateConverter(Long time) {
		if (null != time) {
			return new Date(time);
		} else {
			return null;
		}
	}

	@Named(value = "instantToEpochSecondConverter")
	default Long instantToEpochSecondConverter(Instant instant) {
		if (null != instant) {
			return instant.getEpochSecond();
		} else {
			return null;
		}
	}

	@Named(value = "epochSecondToInstantConverter")
	default Instant epochSecondToInstantConverter(Long epochSecond) {
		if (null != epochSecond) {
			return Instant.ofEpochSecond(epochSecond);
		} else {
			return null;
		}
	}
}
