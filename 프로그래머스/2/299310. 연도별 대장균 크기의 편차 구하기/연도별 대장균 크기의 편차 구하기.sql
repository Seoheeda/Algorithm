SELECT YEAR(A.DIFFERENTIATION_DATE) AS 'YEAR',
(SELECT MAX(B.SIZE_OF_COLONY)
    FROM ECOLI_DATA B
    WHERE YEAR(B.DIFFERENTIATION_DATE) = YEAR(A.DIFFERENTIATION_DATE)) - A.SIZE_OF_COLONY
    AS 'YEAR_DEV', ID

FROM ECOLI_DATA A
ORDER BY YEAR ASC, YEAR_DEV ASC;
