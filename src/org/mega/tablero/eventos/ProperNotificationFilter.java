package org.mega.tablero.eventos;

import java.lang.reflect.Method;

import net.engio.mbassy.listener.IMessageFilter;
import net.engio.mbassy.listener.MessageHandler;

public class ProperNotificationFilter implements IMessageFilter<PropiedadChanged<?, ?>> {

    @Override
    public boolean accepts(PropiedadChanged<?, ?> message, MessageHandler metadata) {
	if(!new ValidMessageFilter().accepts(message, metadata))
	    return false;
	
	Method handler = metadata.getHandler();
	PropiedadesAceptadas config = handler.getAnnotation(PropiedadesAceptadas.class);
	if(config != null) {
	    String[] cProps = config.value();
	    String mProp = message.getPropiedad();
	    
	    for (String cProp : cProps) {
		if(cProp.equals(mProp)) {
		    return true;
		}
	    }
	}
	return false;
    }

}
