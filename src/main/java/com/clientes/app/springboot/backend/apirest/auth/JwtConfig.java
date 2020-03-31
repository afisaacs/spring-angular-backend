package com.clientes.app.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	public static final String RSA_PRIVADO = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAr5hNj5q2wjqyFt7Lv+PxJzLg3Ndn7kBo+eFf4F+bvS2zjSrl\r\n" + 
			"+BH/xAk9R5aP0yK7s0sf9O0Ydq67O1w4o1OVnlUdkSCuJ+THXcwTKd+2g/SgeVHY\r\n" + 
			"Az1zDPw7qsOxTkx0gCNmEzlOLCvuuI5shB1Ve2fYuZ5QTmZGD0AZMUt2YlXy+yIO\r\n" + 
			"s4HXmgcUp74Gp1sAXO+o09dmU/S4N5KjE79pdoIBWFZ5uPS+sRcM3IAjWLcvU9Ae\r\n" + 
			"jbWTK7m6D8fWG6dr95xdrx42WQhA/lk4PCaK8+W7t7ZK1h5XYj7IhaNXu1KRAJUU\r\n" + 
			"Mlq31amNJ8Z7V4J6I7S18wMKwHSlWY/O5+hL/QIDAQABAoIBAEpDSoIyd5q7gjgj\r\n" + 
			"793vBD2Eyg78VusveHY99/OH2/DtVCOfAfWhfx8knTManSc5aFLSseg/IfqHuY3I\r\n" + 
			"WffRUNTOwJtxS9Z5bkvgFDbAc4tpOTk2APqV0YOrZXy6GNG5igfrcK8urD4IMoUB\r\n" + 
			"Tyms0ktHxyPxrZsKrBSlEkKD3MSHSvoAsLnBiNxMsgoQ8yAMtO718Tn18ZJnM8uc\r\n" + 
			"xSZdAT5epAk5mWuDCEijQNSsdeF8dbZL39izkjJTBK1vuYCqFKTciZArGuP/VJNR\r\n" + 
			"tUNBoNe/RWvXggV/05JMDgNruRsxjmmgPvKbtNEc0YYf7mn2HJXVMWooueXGQ1ud\r\n" + 
			"dGAWzg0CgYEA6W4eG5zmL/G5xuneRq0Snop56f+TWG18ptLAAdVAA6OIA/JZBzAx\r\n" + 
			"4cwQb9k3l0W7RFnYxKzwdSWBGs8b/21NnZ1LT3f/kRDqcJCWvPKQJyccavxFpt3K\r\n" + 
			"6szoDrvoke/HznVRcWWZLn15JbU9uUwX6Stc4UxgTvVvUNz9JTs+NLsCgYEAwJKk\r\n" + 
			"svUKjr+4SJcQRnKYW82csVq79oxFMW09sF8u46xaDUkx5NTO1br6R1ajMgflv8fr\r\n" + 
			"RNBrBN2nXTy7n7Qb2yCDxuCnRF/cV7b49eq7i9OCYTVi5fZxbpPFplUIUv+/ig28\r\n" + 
			"8GKk9wkOffmcDCXO+jCCsZGMURd8ZIysYGFQUqcCgYBAsIWjfV3FV6X2XAV/bKNG\r\n" + 
			"UHXsxSkeTmlHCgyv9VsiJwWgol7NwStxuc2sUXQ4ddip3oRWLN8Od/BJb7AAn1ft\r\n" + 
			"IBQo18Z5e0idHVTI1Cfi0dHsTDE5Ryne3B5LRwPyvZKPwq9pT5Cd86PJMWsDmPTf\r\n" + 
			"Z2b8AOCHwTiyHiwBT2Oz1QKBgQCJokWEgg5hqDUV7KH9N7odIv9y4t6kf+yUPvdw\r\n" + 
			"LxdcMbrK9B1s54xMxa5Lfmh1QZ+7Si+kpzFV8PXFdbrDkf62xq5oXfgurjTVnDij\r\n" + 
			"ynjwnuL4hyeAJHs+BO+mye+BIrjK+ranOFmfFiH9d4FITV15A2LldplK+/Y0BzbM\r\n" + 
			"S8EU0QKBgQCu9ZxTw5t4XEmK8CBgOfyHgbNng14GzlQ3aYWaddTDOz0gW8ZMqKf2\r\n" + 
			"cBzYl88xOAXL1jaDl0CpISNI0thp/Xj3435WMXj+TkP+SsX6CayH1PRrd1WoIuu/\r\n" + 
			"+VGnui3qoN80BXGg53TvvJ6GCaOVbrrpCLy23pdugX/IkQpjMioj4w==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICO = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr5hNj5q2wjqyFt7Lv+Px\r\n" + 
			"JzLg3Ndn7kBo+eFf4F+bvS2zjSrl+BH/xAk9R5aP0yK7s0sf9O0Ydq67O1w4o1OV\r\n" + 
			"nlUdkSCuJ+THXcwTKd+2g/SgeVHYAz1zDPw7qsOxTkx0gCNmEzlOLCvuuI5shB1V\r\n" + 
			"e2fYuZ5QTmZGD0AZMUt2YlXy+yIOs4HXmgcUp74Gp1sAXO+o09dmU/S4N5KjE79p\r\n" + 
			"doIBWFZ5uPS+sRcM3IAjWLcvU9AejbWTK7m6D8fWG6dr95xdrx42WQhA/lk4PCaK\r\n" + 
			"8+W7t7ZK1h5XYj7IhaNXu1KRAJUUMlq31amNJ8Z7V4J6I7S18wMKwHSlWY/O5+hL\r\n" + 
			"/QIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";

}
