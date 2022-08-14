# Compose Notes

This app helps you to save your thoughts using **Room**. The goal of this app is to
showcase inserting data loading it from Room DB, implementing **DI**, using *LazyColumn* and **Compose Navigation** and async programming with *Coroutines* and *Flow*.
Also implemented `Custom Dialog` with custom layout

**[`XML Version`](https://github.com/uselesscherry/Notato) of This app with RecyclerView, Jetpack Navigation Component, Custom Exception and Custom Dialog**

### What I used in this project:

- **Jetpack Compose** with [`Compose Navigation`](app/src/main/java/com/cherry/composenotes/presentation/screens/navigation
- [`Koin and Hilt DI`](app/src/main/java/com/cherry/composenotes/di)
- [`Room`](app/src/main/java/com/cherry/composenotes/data/local)
- `Coroutines` and `Flow`
- [`Custom Alert Dialog`](app/src/main/java/com/cherry/composenotes/presentation/components/CustomDialogAlert.kt)

### Getting data from DB and observing it in Fragment:

``` kotlin
    //uses compose.runtime.State
    private val _notes = MutableState<List<Note>>(emptyList())
    val notes: State<List<Note>> = _notes
    
    //loads data from db
    repository.getNotes().onEach { notes ->
            withContext(Dispatchers.IO) {
                _notes.value = notes
            }
        }.launchIn(viewModelScope)
        
    //observing data in Composable function
    @Composable
    fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val notes = viewModel.notes.value
    
    LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes) { note ->
                NoteItem(note)
    }
```
### Custom Alert Dialog for deleting a note:

``` kotlin
@Composable
fun CustomDialog(onDeleteConfirm:()->Unit,onDeleteDismiss:()->Unit) {
    Dialog(onDismissRequest = onDeleteDismiss) {
        CustomDialogUI(onDeleteConfirm = onDeleteConfirm, onDeleteDismiss = onDeleteDismiss)
    }
}

  @Composable
fun CustomDialogUI(
    titleText:String = "Delete note?",
    messageText:String = "Are you sure you want to delete note? There will be no way to restore it.",
    onDeleteConfirm:()->Unit,
    onDeleteDismiss:()->Unit
) {
    Card {
    Icon( imageVector = Icons.Rounded.Delete )

            Column(modifier = Modifier.padding(16.dp)) {
            
                Text(text = titleText, textAlign = TextAlign.Center)
                
                Text(text = messageText, textAlign = TextAlign.Center)
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = onDeleteDismiss) { Text("Cancel") }
                TextButton(onClick = onDeleteConfirm) { Text("Delete") }
            }
        }
    }
    
```
