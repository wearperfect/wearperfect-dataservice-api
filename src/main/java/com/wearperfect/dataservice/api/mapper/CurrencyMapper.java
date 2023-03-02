package com.wearperfect.dataservice.api.mapper;

import com.wearperfect.dataservice.api.dto.CurrencyBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.CurrencyDTO;
import com.wearperfect.dataservice.api.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses= {UtilityMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

    Currency mapCurrencyDtoToCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO mapCurrencyToCurrencyDto(Currency currency);

    CurrencyBasicDetailsDTO mapCurrencyToCurrencyBasicDetailsDto(Currency currency);
}
