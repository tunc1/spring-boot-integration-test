package app.service;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import app.entity.Item;
import app.repository.ItemRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class ItemService
{
	private ItemRepository itemRepository;
	public ItemService(ItemRepository itemRepository)
	{
		this.itemRepository=itemRepository;
	}
	public Item save(Item item)
	{
		return itemRepository.save(item);
	}
	public Item update(Item item)
	{
		return itemRepository.save(item);
	}
	public void deleteById(Long id)
	{
		itemRepository.deleteById(id);
	}
	public Item findById(Long id)
	{
		return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	public Page<Item> findAll(Pageable pageable)
	{
		return itemRepository.findAll(pageable);
	}
}