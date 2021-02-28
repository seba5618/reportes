package ar.com.bambu.models.impl;

import ar.com.bambu.entities.Clientes;
import ar.com.bambu.entities.EvCont;
import ar.com.bambu.entities.EvMedios;
import ar.com.bambu.entities.Eventos;
import ar.com.bambu.entities.TpvConfig;
import ar.com.bambu.models.FacturaElectronicaRequest;
import ar.com.bambu.utils.ConversorDatos;
import jdk.management.resource.internal.inst.StaticInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.Properties;

import static ar.com.bambu.entities.Eventos.*;

public class FacturaDetalle implements Serializable {
    public Double f2_DESCONT = 0d;
    private String f2_XOBS = "";
    private Double d2_PRUNIT;
    private Double c6_DESCONT = 0d;
    private String REMITOS;
    private Double DESCPV;
    private Double VALMERC = 0d;
    private String DOCESPECIE;
    private String DOCSERIE = "X";
    private String SERIE;
    private String CAEE="69260148620357";
    private String EMCAEE="20200606";
    private String NUMDOC;
    private String NUMDOCANT;
    private String VIGENCIA;
    private String NUMERODOCORI;
    private String CUITCLI;
    private String IIBB;
    private Double retencion_IVA_21 = 0d;
    private Double retencion_IVA_105 = 0d;
    private Double retencion_IVA_21_Promo = 0d;
    private Double retencion_IVA_105_Promo = 0d;
    private Double retencion_IVA_21_SinPromo = 0d;
    private Double retencion_IVA_105_SinPromo = 0d;
    private String FECHADIGIT;
    private Double TOTALDOC = 0d;
    private Double TOTIVA;
    private String DIRECC;
    private String LOCALIDAD;
    private String NOMBRECLI;
    private String CONTACTOCLI;
   // private String TIENDA= "C:/pasa/jasperreports/logo.JPG";
    private String TIENDA= "C:/pasa/jasperreports/logo.JPG";
    private String CLIENTE;
    private String DESCCONDPAGO;
    private String copy= "Original";
    private Integer sequence;
    private String TRANSPORTE;
    private String DIRTRANS;
    private String TELTRANS;

    private String a1_TIPO;
    private String TIPODOC;
    private String COD_TIPODOC;
    private String d2_ITEM;
    private String d2_COD;
    private Double d2_QUANT;
    private Double d2_PRCVEN;
    private Double d2_TOTAL;
    private Double IVA21 = 0d;
    private Double IVA105 = 0d;
    private String REMITO;
    private String SERREM;
    private String b1_DESC;
    private String b1_SEGUM;
    private String b1_UM;
    private String letras_ESP;
    private String CODBARRAS="2011790771701006269357354921463201909108";
    //private String CODBARRAS="20117907717010062";
    private Integer NUMVENDDOR;
    private String NOMVENDEDOR;

    private Integer CODCLIENTE;

    private String qr;



    private boolean promocion ;

    public boolean isPromocion() {
        return promocion;
    }
    public void setTipoComprobante(int tipoEvento) {
        if (tipoEvento == TIPO_FACTURA_A) {
            this.DOCSERIE = "A";
            this.SERIE = "A";
            this.setCOD_TIPODOC("01");
        } else if (tipoEvento == TIPO_FACTURA_B) {
            this.DOCSERIE = "B";
            this.setCOD_TIPODOC("06");
        } else if (tipoEvento == TIPO_NOTA_CREDITO_A) {
            this.DOCSERIE = "A";
            this.SERIE = "A";
            this.setCOD_TIPODOC("03");
        } else if (tipoEvento == TIPO_NOTA_CREDITO_B) {
            this.DOCSERIE = "B";
            this.setCOD_TIPODOC("08");
        }else {
            this.DOCSERIE = "X";
            this.setCOD_TIPODOC("04");
        }
    }

    public void setNumeroYTipoComprobante(int caja, long ticket, int tipoEvento) {
        if (tipoEvento == COTIZACION || tipoEvento == REMITOS1 ){
            this.NUMDOC = String.format("%08d", ticket);

        } else {
            this.NUMDOC = String.format("%04d-%08d", caja, ticket);
        }
        if (tipoEvento == TIPO_NOTA_CREDITO_A || tipoEvento == TIPO_NOTA_CREDITO_B)
              this.DOCESPECIE = "NCC";
            else
                this.DOCESPECIE = "NF";
    }

    public void setNumeroYTipoComprobanteAnt(int caja, long ticket, int tipoEvento) {
         if (tipoEvento == TIPO_NOTA_CREDITO_A || tipoEvento == TIPO_NOTA_CREDITO_B)
            this.NUMDOCANT = String.format("%04d-%08d", caja, ticket);;
    }

    public String getQr() {
        //String mock = "{\"ver\":1,\"fecha\":\"2020-10-13\",\"cuit\":30000000007,\"ptoVta\":10,\"tipoCmp\":1,\"nroCmp\":94,\"importe\":12100,\"moneda\":\"DOL\",\"ctz\":65,\"tipoDocRec\":80,\"nroDocRec\":20000000001,\"tipoCodAut\":\"E\",\"codAut\":70417054367476}" ;
        //String base64 = Base64.getEncoder().encodeToString(mock.getBytes(StandardCharsets.UTF_8));
        String cadena = this.qr;
        String base64 = Base64.getEncoder().encodeToString(cadena.getBytes(StandardCharsets.UTF_8));
        return "https://www.afip.gob.ar/fe/qr/?p="+base64;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void armarQr(Eventos ev, TpvConfig tp, Clientes cli) throws JSONException {
        String fecha =  ConversorDatos.fechaInvelATexto((short)ev.getFecha(),ConversorDatos.AAAMMDD_CON_GUIONES_MEDIOS);
        String cuit = "30000000007";// tp.getCuit();
        int ptovta = ev.getSucComprobante();
        int tipoCmp = Integer.parseInt(getCOD_TIPODOC());
        int nroCmp = ev.getNroComprobante();
        double importe = 12100; //hardcodeado sacar de la sumatoria de evmedios;
        String moneda = "PES"; // "DOL"; // en produccion debe ser PES no tenemos la moneda
        double ctz =1; //65; //en producion debe ser 1
        int tipoDocRec= (( this.getCUITCLI().length() <8 )? 90 : 80 );

        Long nroDocRec = Long.parseLong(cli.getCUIT()) ;// "20000000001";
        String tipoCodAut = "E";
        long codAut=  Long.parseLong( this.CAEE);
        // afip {"ver":1,"fecha":"2020-10-13","cuit":30000000007,"ptoVta":10,"tipoCmp":1,"nroCmp":94,"importe":12100,"moneda":"DOL","ctz":65,"tipoDocRec":80,"nroDocRec":20000000001,"tipoCodAut":"E","codAut":70417054367476}
        //mio q {"ver":1,"ctz":1,"tipoDocRec":"80","ptoVta":62,"importe":12100,"tipoCmp":"01","fecha":"20200-6-28","codAut":"69260148620357","cuit":"30000000007","tipoCodAut":"E","nroCmp":10,"nroDocRec":"20000000001","moneda":"DOL"}

        // mi prueba de json
        String message;
        //JSONObject json = new JSONObject();
        String mock = "{\"ver\":1,\"fecha\":\"2020-10-13\",\"cuit\":30000000007,\"ptoVta\":10,\"tipoCmp\":1,\"nroCmp\":94,\"importe\":12100,\"moneda\":\"DOL\",\"ctz\":65,\"tipoDocRec\":80,\"nroDocRec\":20000000001,\"tipoCodAut\":\"E\",\"codAut\":70417054367476}" ;
        JSONObject json = new JSONObject(mock);
        json.put("ver", 1);
        json.put("fecha",fecha);
        json.put("cuit",Long.parseLong(cuit));
        json.put("ptoVta",ptovta);
        json.put("tipoCmp",tipoCmp);
        json.put("nroCmp",nroCmp);
        json.put("importe",importe);
        json.put("moneda",moneda);
        json.put("ctz",ctz);
        json.put("tipoDocRec",tipoDocRec);
        json.put("nroDocRec",nroDocRec);
        json.put("tipoCodAut",tipoCodAut);
        json.put("codAut",codAut);
// lo de arriba sale desordenado
        message = json.toString() ;
//        {"name":"student","course":[{"name":"course1","information":"test","id":3}]}
        //probemos armar un string
        String cadena2 = String.format("{\"ver\":%d,\"fecha\":\"%s\",\"cuit\":%d,\"ptoVta\":%d,\"tipoCmp\":%d,\"nroCmp\":%d,\"importe\":%.0f,\"moneda\":\"%s\",\"ctz\":%d,\"tipoDocRec\":%d,\"nroDocRec\":%d,\"tipoCodAut\":\"%s\",\"codAut\":%d}" , +
                        json.getLong("ver") , json.getString("fecha") , json.getLong("cuit") ,json.getLong("ptoVta"),json.getLong("tipoCmp") ,+
                        json.getLong("nroCmp"),json.getDouble("importe") ,json.getString("moneda"), json.getLong("ctz"), json.getLong("tipoDocRec"),json.getLong("nroDocRec"),json.getString("tipoCodAut"),json.getLong("codAut"));
        setQr(cadena2);

        }

    public void setFechaVigencia(String dosificacion) {
        this.VIGENCIA = dosificacion;
    }

    public void setDetalleFactura(EvCont detalle, int tipoEvento) {
        this.d2_COD = detalle.getCodArticulo()>0 ? String.format("%08d", detalle.getCodArticulo()) : "--------";
        this.b1_DESC = detalle.getArticuloName();
        this.d2_QUANT = detalle.getCantidad();
        this.promocion = detalle.isPromocion();
        if (tipoEvento == TIPO_FACTURA_A || tipoEvento == TIPO_NOTA_CREDITO_A) {
            this.d2_PRUNIT = detalle.getPrecioUnitarioSinIva();
        } else {
            this.d2_PRUNIT = detalle.getPrecioUnitarioConIva();
        }
        this.b1_UM = detalle.getUnidadDeMedida();
    }

    public void setCAEData(FacturaElectronicaRequest req){
        if(req == null){
            return;
        }
        this.CODBARRAS = req.getCodigoBarraFactura();
        this.EMCAEE = req.getVencimientoCAE();
        this.CAEE = req.getCAE();
    }

    public void setDataCliente(Clientes clientes) {
        if (clientes == null) {
            return;
        }
        this.CLIENTE = String.format("%06d", clientes.getCodCliente());
        this.NOMBRECLI = clientes.getNombre();
        this.CUITCLI = clientes.getCUIT();
        this.DIRECC = clientes.getDomicilio();
        this.LOCALIDAD = clientes.getLocalidad();
        this.IIBB = clientes.getIngBrutos();
        this.a1_TIPO = clientes.getCondIvaString();
        this.a1_TIPO = clientes.getCondIvaString();
    }

    public void setDataPieMontoPromociones(List<EvCont> detalle, int tipoEvento) {
        Double totalIvaPromos =0.00;
        this.retencion_IVA_21 = 0.00;
        this.retencion_IVA_105 = 0.00;
        detalle.forEach(c -> {

                    if (tipoEvento == TIPO_FACTURA_A ||tipoEvento == TIPO_NOTA_CREDITO_A ) {
                        this.VALMERC += c.getMontoSinIVA();
                        this.retencion_IVA_21 += c.getMontoIVAComun();
                        this.retencion_IVA_105 += c.getMontoIVAReducido();
                        this.retencion_IVA_21_Promo +=c.getMontoIVAComunPromocion();
                        this.retencion_IVA_105_Promo +=c.getMontoIVAReducidoPromocion();
                        this.retencion_IVA_21_SinPromo +=c.getMontoIVAComunSinPromo();
                        this.retencion_IVA_105_SinPromo +=c.getMontoIVAReducidoSinPromo();
                        f2_DESCONT += c.getMontoPromocionSinIva();
                        this.TOTALDOC = this.VALMERC + retencion_IVA_21 + retencion_IVA_105;
                    } else {
                        this.VALMERC += c.getMontoConIVA();
                        f2_DESCONT += c.getMontoPromocionConIva();
                        this.TOTALDOC = this.VALMERC ;
                    }

                }

        );
          f2_DESCONT *= -1;
        totalIvaPromos = this.retencion_IVA_105_Promo + this.retencion_IVA_21_Promo; //-673
        Double totalIvaSinPromo = this.retencion_IVA_105_SinPromo + this.retencion_IVA_21_SinPromo; //2175.4533
        Double factorIvaReducido = this.retencion_IVA_105_SinPromo / totalIvaSinPromo; //0.1253
        Double factorIvaComun  = this.retencion_IVA_21_SinPromo / totalIvaSinPromo;//0.8746
        Double newIvaReducido =  totalIvaPromos * factorIvaReducido;//77.19
        Double newIvaComun =  totalIvaPromos * factorIvaComun;//538.40
        this.retencion_IVA_21 =  this.retencion_IVA_21_SinPromo + newIvaComun;
        this.retencion_IVA_105 = this.retencion_IVA_105_SinPromo + newIvaReducido;
        newIvaComun = newIvaComun;
    }

    public void setCondicionVenta(EvMedios ev) {
        if (ev != null) {
            this.DESCCONDPAGO = ev.getNombreMedio();
        }
        this.REMITOS = "";
    }

    public void setFechaWithFechaInvel(Eventos ev) {
        if (ev != null) {
            this.FECHADIGIT = ConversorDatos.fechaInvelATexto((short)ev.getFecha(),ConversorDatos.AAAAMMDD);
        }

    }

    public static final int TIPO_FACTURA_B = 17;
    public static final int TIPO_FACTURA_A = 16;
    public static final int COTIZACION= 92;
    public static final int REMITOS1= 11;
    public static final int TIPO_NOTA_CREDITO_B = 20;
    public static final int TIPO_NOTA_CREDITO_A = 19;

    public void setPathLogo(Integer ev,  int tipoEvento) {
        if (ev != null) {
            switch (tipoEvento) {
                case COTIZACION:
                    this.TIENDA = System.getProperty("user.dir") + "\\LOGOS\\" + "\\LogoCotizacion.JPG";
                    break;
                case REMITOS1:
                    this.TIENDA = System.getProperty("user.dir") + "\\LOGOS\\" + "\\LogoRemito.JPG";
                    break;
                case TIPO_FACTURA_A:
                case TIPO_FACTURA_B:
                case TIPO_NOTA_CREDITO_A:
                case TIPO_NOTA_CREDITO_B:
                    this.TIENDA = System.getProperty("user.dir") + "\\LOGOS\\" + "\\LogoFElectronica.JPG";
            }

        }
        System.out.println("***** VIENDO PATH****");
        System.out.println(this.TIENDA);


    }

    public Double getF2_DESCONT() {
        return f2_DESCONT;
    }

    public void setF2_DESCONT(Double f2_DESCONT) {
        this.f2_DESCONT = f2_DESCONT;
    }

    public String getF2_XOBS() {
        return f2_XOBS;
    }

    public void setF2_XOBS(String f2_XOBS) {
        this.f2_XOBS = f2_XOBS;
    }

    public void setF2_XOBS( Integer tipo_evento) throws IOException {

        if( tipo_evento != COTIZACION )
            this.f2_XOBS = "" ;
        else {
            char CR = (char) 0x0D;
            char LF = (char) 0x0A;
            String CRLF = "" + CR + LF;


            String twoLines = "Line1" + CRLF + "Line2";   // 12 characters
            String fichero = System.getProperty("user.dir") + "\\application.properties";
            Properties p = new Properties();
            p.load(new FileReader(fichero));
            this.f2_XOBS = p.getProperty("pie1", "***") + CRLF + p.getProperty("pie2", " ") + CRLF + p.getProperty("pie3", " ") + CRLF + p.getProperty("pie4", " ");
        }

    }

    public Double getD2_PRUNIT() {
        return d2_PRUNIT;
    }

    public void setD2_PRUNIT(Double d2_PRUNIT) {
        this.d2_PRUNIT = d2_PRUNIT;
    }

    public Double getC6_DESCONT() {
        return c6_DESCONT;
    }

    public void setC6_DESCONT(Double c6_DESCONT) {
        this.c6_DESCONT = c6_DESCONT;
    }

    public String getREMITOS() {
        return REMITOS;
    }

    public void setREMITOS(String REMITOS) {
        this.REMITOS = REMITOS;
    }

    public Double getDESCPV() {
        return DESCPV;
    }

    public void setDESCPV(Double DESCPV) {
        this.DESCPV = DESCPV;
    }

    public Double getVALMERC() {
        return VALMERC;
    }

    public void setVALMERC(Double VALMERC) {
        this.VALMERC = VALMERC;
    }

    public String getDOCESPECIE() {
        return DOCESPECIE;
    }

    public void setDOCESPECIE(String DOCESPECIE) {
        this.DOCESPECIE = DOCESPECIE;
    }

    public String getDOCSERIE() {
        return DOCSERIE;
    }

    public void setDOCSERIE(String DOCSERIE) {
        this.DOCSERIE = DOCSERIE;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getCAEE() {
        return CAEE;
    }

    public void setCAEE(String CAEE) {
        this.CAEE = CAEE;
    }

    public String getEMCAEE() {
        return EMCAEE;
    }

    public void setEMCAEE(String EMCAEE) {
        this.EMCAEE = EMCAEE;
    }

    public String getNUMDOC() {
        return NUMDOC;
    }

    public String getNUMDOCANT() {
        return NUMDOCANT;
    }

    public void setNUMDOC(String NUMDOC) {
        this.NUMDOC = NUMDOC;
    }

    public void setNUMDOCANT(String NUMDOCANT) {
        this.NUMDOCANT = NUMDOCANT;
    }

    public String getVIGENCIA() {
        return VIGENCIA;
    }

    public void setVIGENCIA(String VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }


    public String getNUMERODOCORI() {
        return NUMERODOCORI;
    }

    public void setNUMERODOCORI(String NUMERODOCORI) {
        this.NUMERODOCORI = NUMERODOCORI;
    }

    public String getCUITCLI() {
        return CUITCLI;
    }

    public void setCUITCLI(String CUITCLI) {
        this.CUITCLI = CUITCLI;
    }

    public String getIIBB() {
        return IIBB;
    }

    public void setIIBB(String IIBB) {
        this.IIBB = IIBB;
    }

    public Double getRetencion_IVA_21() {
        return retencion_IVA_21;
    }

    public void setRetencion_IVA_21(Double retencion_IVA_21) {
        this.retencion_IVA_21 = retencion_IVA_21;
    }

    public Double getRetencion_IVA_105() {
        return retencion_IVA_105;
    }

    public void setRetencion_IVA_105(Double retencion_IVA_105) {
        this.retencion_IVA_105 = retencion_IVA_105;
    }

    public String getFECHADIGIT() {
        return FECHADIGIT;
    }

    public void setFECHADIGIT(String FECHADIGIT) {
        this.FECHADIGIT = FECHADIGIT;
    }

    public Double getTOTALDOC() {
        return TOTALDOC;
    }

    public void setTOTALDOC(Double TOTALDOC) {
        this.TOTALDOC = TOTALDOC;
    }

    public Double getTOTIVA() {
        return TOTIVA;
    }

    public void setTOTIVA(Double TOTIVA) {
        this.TOTIVA = TOTIVA;
    }

    public String getDIRECC() {
        return DIRECC;
    }

    public void setDIRECC(String DIRECC) {
        this.DIRECC = DIRECC;
    }

    public String getLOCALIDAD() {
        return LOCALIDAD;
    }

    public void setLOCALIDAD(String LOCALIDAD) {
        this.LOCALIDAD = LOCALIDAD;
    }

    public String getNOMBRECLI() {
        return NOMBRECLI;
    }

    public void setNOMBRECLI(String NOMBRECLI) {
        this.NOMBRECLI = NOMBRECLI;
    }

    public String getCONTACTOCLI() {
        return CONTACTOCLI;
    }

    public void setCONTACTOCLI(String CONTACTOCLI) {
        this.CONTACTOCLI = CONTACTOCLI;
    }

    public String getTIENDA() {
        return TIENDA;
    }

    public void setTIENDA(String TIENDA) {
        this.TIENDA = TIENDA;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getDESCCONDPAGO() {
        return DESCCONDPAGO;
    }

    public void setDESCCONDPAGO(String DESCCONDPAGO) {
        this.DESCCONDPAGO = DESCCONDPAGO;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTRANSPORTE() {
        return TRANSPORTE;
    }

    public void setTRANSPORTE(String TRANSPORTE) {
        this.TRANSPORTE = TRANSPORTE;
    }

    public String getDIRTRANS() {
        return DIRTRANS;
    }

    public void setDIRTRANS(String DIRTRANS) {
        this.DIRTRANS = DIRTRANS;
    }

    public String getTELTRANS() {
        return TELTRANS;
    }

    public void setTELTRANS(String TELTRANS) {
        this.TELTRANS = TELTRANS;
    }

    public String getA1_TIPO() {
        return a1_TIPO;
    }

    public void setA1_TIPO(String a1_TIPO) {
        this.a1_TIPO = a1_TIPO;
    }

    public String getTIPODOC() {
        return TIPODOC;
    }

    public void setTIPODOC(String TIPODOC) {
        this.TIPODOC = TIPODOC;
    }

    public String getCOD_TIPODOC() {
        return COD_TIPODOC;
    }

    public void setCOD_TIPODOC(String COD_TIPODOC) {
        this.COD_TIPODOC = COD_TIPODOC;
    }

    public String getD2_ITEM() {
        return d2_ITEM;
    }

    public void setD2_ITEM(String d2_ITEM) {
        this.d2_ITEM = d2_ITEM;
    }

    public String getD2_COD() {
        return d2_COD;
    }

    public void setD2_COD(String d2_COD) {
        this.d2_COD = d2_COD;
    }

    public Double getD2_QUANT() {
        return d2_QUANT;
    }

    public void setD2_QUANT(Double d2_QUANT) {
        this.d2_QUANT = d2_QUANT;
    }

    public Double getD2_PRCVEN() {
        return d2_PRCVEN;
    }

    public void setD2_PRCVEN(Double d2_PRCVEN) {
        this.d2_PRCVEN = d2_PRCVEN;
    }

    public Double getD2_TOTAL() {
        return d2_TOTAL;
    }

    public void setD2_TOTAL(Double d2_TOTAL) {
        this.d2_TOTAL = d2_TOTAL;
    }

    public Double getIVA21() {
        return IVA21;
    }

    public void setIVA21(Double IVA21) {
        this.IVA21 = IVA21;
    }

    public Double getIVA105() {
        return IVA105;
    }

    public void setIVA105(Double IVA105) {
        this.IVA105 = IVA105;
    }

    public String getREMITO() {
        return REMITO;
    }

    public void setREMITO(String REMITO) {
        this.REMITO = REMITO;
    }

    public String getSERREM() {
        return SERREM;
    }

    public void setSERREM(String SERREM) {
        this.SERREM = SERREM;
    }

    public String getB1_DESC() {
        return b1_DESC;
    }

    public void setB1_DESC(String b1_DESC) {
        this.b1_DESC = b1_DESC;
    }

    public String getB1_SEGUM() {
        return b1_SEGUM;
    }

    public void setB1_SEGUM(String b1_SEGUM) {
        this.b1_SEGUM = b1_SEGUM;
    }

    public String getB1_UM() {
        return b1_UM;
    }

    public void setB1_UM(String b1_UM) {
        this.b1_UM = b1_UM;
    }

    public String getLetras_ESP() {
        return letras_ESP;
    }

    public void setLetras_ESP(String letras_ESP) {
        this.letras_ESP = letras_ESP;
    }

    public String getCODBARRAS() {
        return CODBARRAS;
    }

    public void setCODBARRAS(String CODBARRAS) {
        this.CODBARRAS = CODBARRAS;
    }

    public Integer getNUMVENDDOR() {
        return NUMVENDDOR;
    }

    public void setNUMVENDDOR(Integer NUMVENDDOR) {
        this.NUMVENDDOR = NUMVENDDOR;
    }

    public String getNOMVENDEDOR() {
        return NOMVENDEDOR;
    }

    public void setNOMVENDEDOR(String NOMVENDEDOR) {
        this.NOMVENDEDOR = NOMVENDEDOR;
    }

    public Integer getCODCLIENTE() {
        return CODCLIENTE;
    }

    public void setCODCLIENTE(Integer CODCLIENTE) {
        this.CODCLIENTE = CODCLIENTE;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "f2_DESCONT=" + f2_DESCONT +
                ", f2_XOBS='" + f2_XOBS + '\'' +
                ", d2_PRUNIT=" + d2_PRUNIT +
                ", c6_DESCONT=" + c6_DESCONT +
                ", REMITOS='" + REMITOS + '\'' +
                ", DESCPV=" + DESCPV +
                ", VALMERC=" + VALMERC +
                ", DOCESPECIE='" + DOCESPECIE + '\'' +
                ", DOCSERIE='" + DOCSERIE + '\'' +
                ", SERIE='" + SERIE + '\'' +
                ", CAEE='" + CAEE + '\'' +
                ", EMCAEE='" + EMCAEE + '\'' +
                ", NUMDOC='" + NUMDOC + '\'' +
                ", VIGENCIA='" + VIGENCIA + '\'' +
                ", NUMERODOCORI='" + NUMERODOCORI + '\'' +
                ", CUITCLI='" + CUITCLI + '\'' +
                ", IIBB='" + IIBB + '\'' +
                ", retencion_IVA_21=" + retencion_IVA_21 +
                ", retencion_IVA_105=" + retencion_IVA_105 +
                ", FECHADIGIT='" + FECHADIGIT + '\'' +
                ", TOTALDOC=" + TOTALDOC +
                ", TOTIVA=" + TOTIVA +
                ", DIRECC='" + DIRECC + '\'' +
                ", LOCALIDAD='" + LOCALIDAD + '\'' +
                ", NOMBRECLI='" + NOMBRECLI + '\'' +
                ", CONTACTOCLI='" + CONTACTOCLI + '\'' +
                ", TIENDA='" + TIENDA + '\'' +
                ", CLIENTE='" + CLIENTE + '\'' +
                ", DESCCONDPAGO='" + DESCCONDPAGO + '\'' +
                ", copy='" + copy + '\'' +
                ", sequence=" + sequence +
                ", TRANSPORTE='" + TRANSPORTE + '\'' +
                ", DIRTRANS='" + DIRTRANS + '\'' +
                ", TELTRANS='" + TELTRANS + '\'' +
                ", a1_TIPO='" + a1_TIPO + '\'' +
                ", TIPODOC='" + TIPODOC + '\'' +
                ", COD_TIPODOC='" + COD_TIPODOC + '\'' +
                ", d2_ITEM='" + d2_ITEM + '\'' +
                ", d2_COD='" + d2_COD + '\'' +
                ", d2_QUANT=" + d2_QUANT +
                ", d2_PRCVEN=" + d2_PRCVEN +
                ", d2_TOTAL=" + d2_TOTAL +
                ", IVA21=" + IVA21 +
                ", IVA105=" + IVA105 +
                ", REMITO='" + REMITO + '\'' +
                ", SERREM='" + SERREM + '\'' +
                ", b1_DESC='" + b1_DESC + '\'' +
                ", b1_SEGUM='" + b1_SEGUM + '\'' +
                ", b1_UM='" + b1_UM + '\'' +
                ", letras_ESP='" + letras_ESP + '\'' +
                ", CODBARRAS='" + CODBARRAS + '\'' +
                '}';
    }
}
