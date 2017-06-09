package bd2.Muber.services.impl;

import bd2.Muber.dto.*;

public  class BaseServiceImpl{
	
	protected DTOFactory dtoFactory;

	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}

	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}
	
}