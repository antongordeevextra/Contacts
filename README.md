# Contacts

Task 5.
Приложение отображаем список контактов с возможностью редактирования, поддерживается режим работы для планшета.

Task6.
Добавлена возможность удаления и поиска контактов с помощью SearchView. Так же добавлены Diffutil и ItemDecoration в работу адаптера.

# Примеры кода
Часть кода реализации адаптера 
```kotlin
private val diffCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var contacts: List<Contact>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    //...
```

Реализация поиска контакта по введенному запросу
```kotlin
override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                filterList(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String) {
        val searchQuery = query.lowercase()

        if (searchQuery.isNotEmpty())  {
            adapter.contacts = list.filter {
                it.toString().lowercase().contains(searchQuery)
            }
        } else {
            adapter.contacts = list
        }
    }
```
Поддержка планшетного режима
```kotlin
override fun launchSecondFragment(contactId: Int) {
        if (findViewById<FrameLayout>(R.id.fragment_container_2) != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_2, DetailContactFragment.newInstance(contactId))
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailContactFragment.newInstance(contactId))
                .addToBackStack(null)
                .commit()
        }
    }
```
    

