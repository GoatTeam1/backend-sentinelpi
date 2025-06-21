# backend-sentinelpi

Repositorio donde teenemos alojado el codigo del sistema backend para el proyecto de SentinelPI, este repositorio utiliza el modelo **Gitflow** para organizar el desarrollo del software. A continuación se documenta la estrategia de ramas y las buenas prácticas para trabajar de forma consistente.

---

## Estructura de ramas

### Ramas principales

- main: Contiene el código **en producción**. Todo aquí debe estar probado y estable.
- develop: Contiene el código en **estado de integración**, listo para pruebas antes de producción.

---

### Ramas auxiliares

| Tipo de rama     | Origen     | Fusión hacia         | Uso principal                               |
|------------------|------------|-----------------------|----------------------------------------------|
| feature/*        | develop    | develop               | Para nuevas funcionalidades.                 |
| bugfix/*         | develop    | develop               | Corrección de errores encontrados en pruebas.|
| release/*        | develop    | main + develop        | Preparar una nueva versión para producción.  |
| hotfix/*         | main       | main + develop        | Corrección urgente de errores en producción. |

---

## Flujo de trabajo

1. Nueva funcionalidad

    git checkout develop  
    git checkout -b feature/nombre-funcionalidad  
    # trabajar y commitear  
    git checkout develop  
    git merge feature/nombre-funcionalidad  
    git branch -d feature/nombre-funcionalidad  

2. Nueva versión

    git checkout develop  
    git checkout -b release/1.0.0  
    # ajustes finales  
    git checkout main  
    git merge release/1.0.0  
    git tag -a v1.0.0 -m "Release 1.0.0"  
    git checkout develop  
    git merge release/1.0.0  
    git branch -d release/1.0.0  

3. Corrección urgente en producción

    git checkout main  
    git checkout -b hotfix/fix-nombre-error  
    # aplicar fix  
    git checkout main  
    git merge hotfix/fix-nombre-error  
    git tag -a v1.0.1 -m "Hotfix 1.0.1"  
    git checkout develop  
    git merge hotfix/fix-nombre-error  
    git branch -d hotfix/fix-nombre-error  

---

## Buenas prácticas de commits

- Commits claros y concisos. Evitar "arreglo", "cambio", etc.
- Usar prefijos para identificar el tipo de cambio:
  - feat: para nuevas funcionalidades
  - fix: para correcciones de bugs
  - docs: para cambios en documentación
  - refactor: para cambios de código que no afectan la funcionalidad
  - test: para agregar o modificar pruebas
  - chore: para tareas menores (build, configs, etc.)

### Ejemplos de buenos mensajes de commit

    feat: agrega autenticación con JWT  
    fix: corrige error al validar email duplicado  
    docs: actualiza sección de instalación  
    refactor: separa lógica de validación en servicio aparte  

---

## Notas finales

- Siempre trabajar en ramas, nunca directo en main o develop.
- Hacer pull antes de empezar a trabajar.
- Borrar ramas locales cuando ya no se usen.
- Usar tags para marcar versiones en producción.
