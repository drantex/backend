package com.slc.configure;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * GenericResponse
 * <p/>
 * Clase que se utiliza para el envio de resupestas al front-end
 *
 * @author Stiven Lenis
 * @since 08/12/2015
 * Historia de Modificaciones
 * -------------------------------------------------------------
 * Autor	            Fecha           Modificacion
 * -----------------  -------------- ----------------------------
 */

@XmlRootElement
public class GenericResponse
{
    private String errorCode;
    private String errorMessage;
    private Object object;
    private ResultStatus resultStatus;

    /**
     *
     */
    public GenericResponse()
    {

    }

    /**
     * Metodo que retorna el estado de una respuesta
     *
     * @return estado de respuesta
     */
    public ResultStatus getResultStatus()
    {
        return resultStatus;
    }

    /**
     * Metodo que recibe el estado de una respuesta
     *
     * @param resultStatus
     */
    public void setResultStatus(ResultStatus resultStatus)
    {
        this.resultStatus = resultStatus;
    }

    /**
     * Metodo que retorna el codigo de error
     *
     * @return codigo de error
     */
    public String getErrorCode()
    {
        return errorCode;
    }

    /**
     * Metodo que recibe el codigo de error
     *
     * @param errorCode
     */
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     * Metodo que retorna el mensaje de error de una respuesta
     *
     * @return
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Metodo que modifica el mensaje de error a mostrar
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    /**
     * Metodo que retorna el objeto que es enviado en una respuesta
     *
     * @return Object
     */
    public Object getObject()
    {
        return object;
    }

    /**
     * Metodo que modifica el objeto a ser enviado en una respuesta
     *
     * @param object
     */
    public void setObject(Object object)
    {
        try {
            System.out.println("Acaba de llegar al metodo del Generic");
            this.object = new Gson().toJson(object);
        } catch (Exception e) {
            System.out.println("excepcion Generic");
            e.printStackTrace();
        }
        
    }

}