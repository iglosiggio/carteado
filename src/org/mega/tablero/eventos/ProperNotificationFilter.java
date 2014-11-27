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
	    Class<?> cSender = config.desde();
	    Object mSender = message.getSender();
	    Class<?> cTipo = config.dato();
	    Class<?> mTipo = message.getTipoDato();
	    String[] cProps = config.propiedades();
	    String mProp = message.getPropiedad();
	    // Es el sender compatible con el esperado?
	    if(!cSender.isInstance(mSender))
		return false;
	    // Puedo hacer TipoEsperado algo = nuevoDato?
	    if(!mTipo.isAssignableFrom(cTipo))
		return false;
	    // Si no digo ninguna prop por defecto entiendo que listeneo a todos
	    if(cProps.length == 0)
		return true;
	    // Coincide la propiedad?
	    for (String cProp : cProps) {
		if(cProp.equals(mProp)) {
		    return true;
		}
	    }
	}
	return false;
    }

}
